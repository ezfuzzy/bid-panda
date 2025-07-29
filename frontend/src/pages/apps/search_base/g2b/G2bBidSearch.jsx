// SearchByIndstrytyCd.jsx
import React, { useState } from "react"
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css"
import BidList from "../G2bBidList"
import Toast from "components/Toast"
import SaveOptionsModal from "components/SaveOptionsModal"
import ApiTestSection from "pages/apps/search_base/g2b/ApiTestSection"
import { useToast } from "hooks/useToast"
import { BidApiService } from "../bidApiService"
import { BID_SEARCH_CONSTANTS, createDefaultDates } from "constants/mapping"
import { getRegionNameByCode, downloadFile, copyToClipboard, filterUrgentBids, filterBidItems, calculatePagination, calculatePreviousPeriod } from "utils/bidUtils"
import { regionOptions } from "constants/mapping"
import { transformResponseToBiddingNoticeDto } from "utils/transformResponseToBiddingNoticeDto"

// eslint-disable-next-line
const { PRESET_CODES, PAGINATION, MESSAGES, EXCLUDE_NOTICE_TYPES, REGION } = BID_SEARCH_CONSTANTS

const G2bBidSearch = () => {
  const { toast, showToast, hideToast } = useToast()
  const { today, defaultEndDate } = createDefaultDates()

  // 검색 상태
  const [bidNtceNm, setBidNtceNm] = useState("")
  const [excludeKeyword, setExcludeKeyword] = useState("")
  const [regionCode, setRegionCode] = useState("")
  const [indstrytyCd, setIndstrytyCd] = useState("")
  const [activeCode, setActiveCode] = useState(null)
  const [startDate, setStartDate] = useState(today)
  const [endDate, setEndDate] = useState(defaultEndDate)

  // 데이터 상태
  const [data, setData] = useState([])
  const [currentData, setCurrentData] = useState([])
  const [currentPage, setCurrentPage] = useState(1)
  const [totalCount, setTotalCount] = useState(null)

  // UI 상태
  const [searchLoading, setSearchLoading] = useState(false)
  const [saveLoading, setSaveLoading] = useState(false)
  const [showSaveOptions, setShowSaveOptions] = useState(false)
  const [filtering, setFiltering] = useState(false)
  const [error, setError] = useState(null)
  const [sysMessage, setSysMessage] = useState("")
  const [showUrgentOnly, setShowUrgentOnly] = useState(false)

  // 데이터 개수 확인
  const checkDataCount = async (code = null) => {
    const finalCode = code || indstrytyCd
    if (!startDate || !endDate) {
      alert(MESSAGES.DATE_SELECTION_ERROR)
      return null
    }

    setSearchLoading(true)
    setError(null)
    setData([])

    try {
      const searchParams = {
        startDate,
        endDate,
        bidNtceNm,
        indstrytyCd: finalCode,
        regionCode,
      }

      const count = await BidApiService.checkDataCount(searchParams)
      setTotalCount(count)

      if (count > PAGINATION.MAX_SEARCH_RESULTS) {
        setSysMessage(MESSAGES.MAX_RESULTS_WARNING)
        return PAGINATION.MAX_SEARCH_RESULTS
      }
      return count
    } catch (err) {
      setError(err.message)
      return null
    } finally {
      setSearchLoading(false)
    }
  }

  // 지역 정보 조회
  const fetchRegionInfo = async (filtered, page) => {
    return await Promise.all(
      filtered.map(async (item, idx) => {
        try {
          const prtcptPsblRgnNm = await BidApiService.fetchRegionInfo(item.bidNtceNo, item.bidNtceOrd)
          return {
            ...item,
            listOrder: (page - 1) * PAGINATION.ROWS_PER_PAGE + idx + 1,
            prtcptPsblRgnNm,
          }
        } catch (error) {
          console.error("지역 정보 조회 실패:", error)
          return {
            ...item,
            listOrder: (page - 1) * PAGINATION.ROWS_PER_PAGE + idx + 1,
            prtcptPsblRgnNm: REGION.API_ERROR_NAME,
          }
        }
      })
    )
  }

  // 메인 데이터 조회
  const fetchData = async (code = null, page = 1) => {
    const finalCode = code || indstrytyCd
    const count = await checkDataCount(code)

    if (!count) return

    setSearchLoading(true)
    setFiltering(true)
    setError(null)
    setCurrentData([])
    setData([])

    try {
      const searchParams = {
        startDate,
        endDate,
        bidNtceNm,
        indstrytyCd: finalCode,
        regionCode,
      }

      const rawItems = await BidApiService.fetchBidList(searchParams, count)
      const filtered = filterBidItems(rawItems, excludeKeyword)

      let itemsWithRegion
      if (regionCode) {
        itemsWithRegion = filtered.map((item, idx) => ({
          ...item,
          listOrder: (page - 1) * PAGINATION.ROWS_PER_PAGE + idx + 1,
          prtcptPsblRgnNm: getRegionNameByCode(regionCode) || REGION.NO_INFO_NAME,
        }))
      } else {
        itemsWithRegion = await fetchRegionInfo(filtered, page)
      }

      const validItems = itemsWithRegion.filter((item) => item !== null)
      const transformedItems = validItems.map((item, idx) => {
        const dtoItem = transformResponseToBiddingNoticeDto(item)
        return {
          ...dtoItem,
          listOrder: (page - 1) * PAGINATION.ROWS_PER_PAGE + idx + 1,
        }
      })

      const { startIdx, endIdx } = calculatePagination(transformedItems.length, 1)

      setData(transformedItems)
      setCurrentData(transformedItems.slice(startIdx, endIdx))
      setTotalCount(transformedItems.length)

      console.log(transformedItems)
    } catch (err) {
      setError(err.message)
      console.error(err)
    } finally {
      setSearchLoading(false)
      setFiltering(false)
    }
  }

  // 데이터 저장
  const storeData = async (dataToSave, saveType) => {
    setSaveLoading(true)
    setShowSaveOptions(false)

    try {
      console.log(`${saveType} 저장 시작:`, dataToSave.length, "개 항목")
      const response = await BidApiService.saveToDatabase(dataToSave)

      const successCount = response.data.length
      const failCount = dataToSave.length - successCount

      console.log(`저장 완료 - 성공: ${successCount}개, 실패: ${failCount}개`)

      if (failCount === 0) {
        showToast(`${saveType} ${successCount}개 항목이 모두 성공적으로 저장되었습니다.`, "success")
      } else if (successCount === 0) {
        showToast(`${saveType} 저장 완료 - 성공: ${successCount}개, 실패: ${failCount}개`, "error")
      } else {
        showToast(`${saveType} 저장 완료 - 성공: ${successCount}개, 실패: ${failCount}개`, "warning")
      }
    } catch (error) {
      console.error("저장 중 오류 발생:", error)
      showToast(MESSAGES.SAVE_ERROR, "error")
    } finally {
      setSaveLoading(false)
    }
  }

  // 업종코드 클릭 핸들러
  const handleCodeClick = (code) => {
    if (indstrytyCd === code) {
      setIndstrytyCd("")
      setActiveCode(null)
    } else {
      setIndstrytyCd(code)
      setActiveCode(code)
    }
  }

  // 페이지 변경 핸들러
  const handlePageChange = (newPage) => {
    const { startIdx, endIdx } = calculatePagination(totalCount, newPage)

    if (!data || data.length === 0 || startIdx >= data.length) {
      setCurrentData([])
    } else {
      const pageData = data.slice(startIdx, endIdx)
      setCurrentData(pageData)
    }
    setCurrentPage(newPage)
  }

  // 폼 초기화
  const resetForm = () => {
    setBidNtceNm("")
    setExcludeKeyword("")
    setRegionCode("")
    setIndstrytyCd("")
    setActiveCode(null)
    setStartDate(today)
    setEndDate(defaultEndDate)
    setSearchLoading(false)
    setSaveLoading(false)
    setCurrentData([])
    setData([])
    setError(null)
    setCurrentPage(1)
    setTotalCount(null)
  }

  // 마감임박 필터링
  const handleUrgentBid = () => {
    if (!showUrgentOnly) {
      const urgentData = filterUrgentBids(data)
      setCurrentData(urgentData)
    } else {
      setCurrentData(data.slice(0, PAGINATION.ROWS_PER_PAGE))
      setCurrentPage(1)
    }
    setShowUrgentOnly((prev) => !prev)
  }

  // 이전 기간 설정
  const handlePreviousPeriod = () => {
    const { newStartDate, newEndDate } = calculatePreviousPeriod(startDate)
    setStartDate(newStartDate)
    setEndDate(newEndDate)
  }

  // DB에서 데이터 조회
  const fetchDataFromDB = (page = 0) => {
    const { startIdx, endIdx } = calculatePagination(PAGINATION.ROWS_PER_PAGE, 1)

    BidApiService.fetchFromDatabase(page)
      .then((res) => {
        console.log("DB 데이터:", res.data.content)
        setData(res.data.content)
        setCurrentData(res.data.content.slice(startIdx, endIdx))
        showToast("DB에서 데이터를 성공적으로 가져왔습니다", "success")
      })
      .catch((err) => {
        console.error("DB 데이터 조회 실패:", err)
        showToast("DB 데이터 조회 중 오류가 발생했습니다", "error")
      })
  }

  const totalPages = Math.ceil(totalCount / PAGINATION.ROWS_PER_PAGE)

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto p-4 max-w-6xl">
        {/* 헤더 */}
        <div className="bg-white rounded-lg shadow-sm p-4 mb-6">
          <h1 className="text-xl font-bold text-gray-800 mb-2">나라장터 입찰공고(용역) 검색</h1>
          <p className="text-sm text-gray-600">개찰일(마감일) 기준으로 검색합니다</p>
        </div>

        {/* 검색 폼 */}
        <div className="bg-white rounded-lg shadow-sm p-4 mb-6">
          <div className="space-y-4">
            {/* 검색어 입력 */}
            <div className="space-y-3">
              <div className="grid grid-cols-1 lg:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">🔍 공고 제목에 포함될 단어</label>
                  <input
                    type="text"
                    value={bidNtceNm}
                    onChange={(e) => setBidNtceNm(e.target.value)}
                    onKeyDown={(e) => {
                      if (e.key === "Enter") {
                        setCurrentPage(1)
                        fetchData(null, 1)
                      }
                    }}
                    placeholder="예: 청소, 보안, 급식 등"
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">❌ 제외할 단어</label>
                  <input
                    type="text"
                    value={excludeKeyword}
                    onChange={(e) => setExcludeKeyword(e.target.value)}
                    onKeyDown={(e) => {
                      if (e.key === "Enter") {
                        setCurrentPage(1)
                        fetchData(null, 1)
                      }
                    }}
                    placeholder="예: 청소년, 학원 등"
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                  />
                </div>
              </div>
            </div>

            {/* 날짜 선택 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">📅 검색 기간</label>
              <div className="flex flex-col sm:flex-row gap-3 items-start sm:items-center">
                <div className="flex items-center gap-2">
                  <DatePicker
                    selected={startDate}
                    onChange={(date) => {
                      const adjusted = new Date(date)
                      adjusted.setHours(0, 0, 0, 0)
                      setStartDate(adjusted)
                    }}
                    dateFormat="yyyy-MM-dd"
                    className="px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                  />
                  <span className="text-gray-500">~</span>
                  <DatePicker
                    selected={endDate}
                    onChange={(date) => {
                      const adjusted = new Date(date)
                      adjusted.setHours(23, 59, 0, 0)
                      setEndDate(adjusted)
                    }}
                    dateFormat="yyyy-MM-dd"
                    className="px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                  />
                </div>
                <button onClick={handlePreviousPeriod} className="px-4 py-2 text-sm bg-gray-100 text-gray-700 rounded-md hover:bg-gray-200 transition-colors whitespace-nowrap">
                  📆 1달 전 기간
                </button>
              </div>
            </div>

            {/* 업종코드와 지역코드 */}
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">🏢 업종코드 (선택사항)</label>
                <input
                  type="text"
                  value={indstrytyCd}
                  onChange={(e) => {
                    setIndstrytyCd(e.target.value)
                    setActiveCode(null)
                  }}
                  onKeyDown={(e) => {
                    if (e.key === "Enter") {
                      setCurrentPage(1)
                      fetchData(null, 1)
                    }
                  }}
                  placeholder="예: 1172"
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">🌍 지역 선택 (선택사항 - 전국이 아닌 지역을 선택하면 전국은 안나옵니다.)</label>
                <select
                  value={regionCode}
                  onChange={(e) => {
                    setRegionCode(e.target.value)
                    setActiveCode(null)
                  }}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent bg-white">
                  <option value="">전체 지역</option>
                  {regionOptions.map((region) => (
                    <option key={region.code} value={region.code}>
                      {region.name}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* 업종코드 빠른 선택 */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">⚡ 자주 사용하는 업종코드</label>
              <div className="flex flex-wrap gap-2">
                {PRESET_CODES.map((code) => (
                  <button
                    key={code}
                    onClick={() => handleCodeClick(code)}
                    className={`px-3 py-1 text-sm rounded-full border transition-all ${
                      activeCode === code || indstrytyCd === code
                        ? "bg-blue-500 text-white border-blue-500 shadow-sm"
                        : "bg-white text-gray-600 border-gray-300 hover:border-blue-300 hover:bg-blue-50"
                    }`}>
                    {code}
                  </button>
                ))}
              </div>
            </div>

            {/* 검색 버튼 */}
            <div className="flex gap-3 pt-2">
              <button
                onClick={() => {
                  setCurrentPage(1)
                  fetchData(null, 1)
                }}
                className="flex-1 sm:flex-none px-6 py-3 bg-blue-600 text-white font-medium rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
                disabled={searchLoading}>
                {searchLoading ? MESSAGES.SEARCH_LOADING : MESSAGES.SEARCH_BUTTON}
              </button>

              <button
                onClick={() => setShowSaveOptions(true)}
                className="flex-1 sm:flex-none px-6 py-3 bg-blue-100 font-medium rounded-lg hover:bg-blue-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
                disabled={saveLoading || searchLoading || (!data?.length && !currentData?.length)}>
                {saveLoading ? (
                  <span className="flex items-center justify-center">
                    <svg className="animate-spin -ml-1 mr-2 h-4 w-4 text-blue-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                      <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                      <path
                        className="opacity-75"
                        fill="currentColor"
                        d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                    </svg>
                    저장중...
                  </span>
                ) : (
                  "💾 DB에 저장하기"
                )}
              </button>

              <button
                onClick={resetForm}
                className="px-4 py-3 bg-gray-100 text-gray-700 font-medium rounded-lg hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2 transition-all">
                ↺ 초기화
              </button>
            </div>

            {/* API 테스트 섹션 */}
            <ApiTestSection fetchDataFromDB={fetchDataFromDB} showToast={showToast} />
          </div>
        </div>

        {/* 오류 메시지 */}
        {error && (
          <div className="bg-red-50 border border-red-200 rounded-lg p-4 mb-6">
            <div className="flex items-center">
              <span className="text-red-500 mr-2">❌</span>
              <p className="text-red-700">오류: {error}</p>
            </div>
          </div>
        )}

        {/* 검색 결과 */}
        {totalCount && (
          <div className="bg-white rounded-lg shadow-sm p-4 mb-6">
            <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
              <div>
                <h2 className="text-lg font-semibold text-gray-800">검색 결과</h2>
                <div className="flex items-center gap-2 mt-1">
                  <span className="text-2xl font-bold text-blue-600">{totalCount}</span>
                  <span className="text-gray-600">개의 공고를 찾았습니다</span>
                </div>
                {sysMessage && <p className="text-sm text-gray-500 mt-1">{sysMessage}</p>}
                {filtering && (
                  <div className="flex items-center text-orange-600 text-sm mt-1">
                    <span className="animate-spin mr-2">⏳</span>
                    {MESSAGES.FILTERING}
                  </div>
                )}
              </div>

              <div className="flex flex-col sm:flex-row gap-2 w-full sm:w-auto">
                <button
                  onClick={handleUrgentBid}
                  className={`px-4 py-2 font-medium rounded-lg transition-all ${
                    showUrgentOnly ? "bg-red-500 text-white hover:bg-red-600" : "bg-red-50 text-red-600 border border-red-200 hover:bg-red-100"
                  }`}>
                  {showUrgentOnly ? "📋 전체 보기" : "⚡ 마감임박 (3일 이내)"}
                </button>
              </div>
            </div>

            <div className="flex flex-col sm:flex-row gap-2 mt-4 pt-4 border-t border-gray-100">
              <button
                onClick={() => copyToClipboard(data, indstrytyCd)}
                className="flex-1 sm:flex-none px-4 py-2 bg-green-50 text-green-700 border border-green-200 font-medium rounded-lg hover:bg-green-100 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2 transition-all">
                📋 목록 복사
              </button>
              <button
                onClick={() => downloadFile(data, indstrytyCd)}
                className="flex-1 sm:flex-none px-4 py-2 bg-blue-50 text-blue-700 border border-blue-200 font-medium rounded-lg hover:bg-blue-100 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all">
                💾 다운로드
              </button>
            </div>
          </div>
        )}

        {/* 결과 리스트 */}
        {data.length > 0 && (
          <div className="bg-white rounded-lg shadow-sm">
            <BidList items={currentData} currentPage={currentPage} totalPages={totalPages} onPageChange={handlePageChange} showUrgentOnly={showUrgentOnly} showToast={showToast} />
          </div>
        )}

        {/* 모달 및 토스트 */}
        <SaveOptionsModal
          showSaveOptions={showSaveOptions}
          onClose={() => setShowSaveOptions(false)}
          onSave={storeData}
          currentData={currentData}
          data={data}
          saveLoading={saveLoading}
        />

        <Toast toast={toast} onClose={hideToast} />
      </div>
    </div>
  )
}

export default G2bBidSearch
