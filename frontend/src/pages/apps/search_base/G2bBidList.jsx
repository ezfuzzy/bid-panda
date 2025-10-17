// BidList.jsx
import React, { useState } from "react"
import Modal from "react-modal"
import axios from "axios"
import { BID_SEARCH_CONSTANTS } from "constants/mapping"
import { BidApiService } from "pages/apps/search_base/bidApiService"

const { API } = BID_SEARCH_CONSTANTS

Modal.setAppElement("#root")

const formatDate = (datetime) => datetime?.split("T")[0]

const BidList = ({ items, currentPage, totalPages, onPageChange, showToast }) => {
  const [selected, setSelected] = useState(null)
  const [isProcessing, setIsProcessing] = useState(false)
  const [savingItems, setSavingItems] = useState(new Set())
  const [resultItems, setResultItems] = useState(new Set())
  const [bidResultModal, setBidResultModal] = useState(null) // 개찰결과 모달 상태
  const [bidResultData, setBidResultData] = useState(null) // 개찰결과 데이터
  const [savingResult, setSavingResult] = useState(false) // 개찰결과 저장 중 상태

  const pagesPerGroup = 10

  const totalPageGroups = Math.ceil(totalPages / pagesPerGroup)
  const currentGroup = Math.floor((currentPage - 1) / pagesPerGroup)
  const pageNumbers = Array.from({ length: Math.min(pagesPerGroup, totalPages - currentGroup * pagesPerGroup) }, (_, i) => currentGroup * pagesPerGroup + i + 1)

  const goToPage = (page) => {
    onPageChange(page)
  }

  const goToPrevGroup = () => {
    if (currentGroup > 0) {
      const prevGroupStart = (currentGroup - 1) * pagesPerGroup + 1
      onPageChange(prevGroupStart)
    }
  }

  const goToNextGroup = () => {
    if (currentGroup < totalPageGroups - 1) {
      const nextGroupStart = (currentGroup + 1) * pagesPerGroup + 1
      onPageChange(nextGroupStart)
    }
  }

  // 개찰일시가 지났는지 확인하는 함수
  const isOpengDatePassed = (opengDt) => {
    if (!opengDt) return false
    const opengDate = new Date(opengDt)
    const now = new Date()
    return opengDate < now
  }

  // 개별 아이템 저장 함수
  const saveItem = async (item) => {
    const itemKey = `${item.bidNtceNo}-${item.listOrder}`

    if (savingItems.has(itemKey)) return

    setSavingItems((prev) => new Set([...prev, itemKey]))

    try {
      const response = await axios.post("/api/bidding-notices", item)
      console.log("저장 성공:", response)
      showToast(`${item.bidNtceNm} 저장 완료 - 성공: 1개`)
    } catch (error) {
      console.error("저장 실패:", error)
      showToast("저장 중 오류가 발생했습니다.", "error")
    } finally {
      setSavingItems((prev) => {
        const newSet = new Set(prev)
        newSet.delete(itemKey)
        return newSet
      })
    }
  }

  // 개찰결과 조회
  const getBidResult = async (item) => {
    const itemKey = `${item.bidNtceNo}-${item.listOrder}`

    if (resultItems.has(itemKey)) return

    setResultItems((prev) => new Set([...prev, itemKey]))
    setBidResultModal(item)

    try {
      const bid_result_item = await BidApiService.fetchBidResult(item)

      setBidResultData(bid_result_item)
    } catch (error) {
      console.error("개찰결과 불러오기 실패:", error)
      showToast("개찰결과를 불러오는 중 오류가 발생했습니다.", "error")
      setBidResultModal(null)
    } finally {
      setResultItems((prev) => {
        const newSet = new Set(prev)
        newSet.delete(itemKey)
        return newSet
      })
    }
  }

  // 개찰결과 저장 함수 (DB에서 불러온 데이터용)
  const saveBidResultWithId = async (item, bidResultData) => {
    try {
      const response = await axios.post(`${API.BASE_URL_BACKEND_API}/${item.id}/result`, bidResultData)
      console.log("개찰결과 저장 성공:", response)
      showToast("개찰결과가 성공적으로 저장되었습니다.")
    } catch (error) {
      console.error("개찰결과 저장 실패:", error)
      showToast("개찰결과를 저장하는 중 오류가 발생했습니다.", "error")
      throw error
    }
  }

  // 개찰결과 저장 함수 (외부 API에서 불러온 데이터용)
  const saveBidResultWithBidNo = async (bidResultData) => {
    try {
      const response = await axios.post(`${API.BASE_URL_BACKEND_API}/by-bid-no/result`, bidResultData)
      console.log("개찰결과 저장 성공:", response)
      showToast("개찰결과가 성공적으로 저장되었습니다.")
    } catch (error) {
      console.error("개찰결과 저장 실패:", error)
      showToast("개찰결과를 저장하는 중 오류가 발생했습니다.", "error")
      throw error
    }
  }

  // 개찰결과 저장 핸들러
  const handleSaveBidResult = async () => {
    if (!bidResultModal || !bidResultData) return

    setSavingResult(true)

    try {
      // DB에서 불러온 데이터인지 외부 API에서 불러온 데이터인지 구분
      if (bidResultModal.id) {
        // DB에서 불러온 데이터 (id 존재)
        await saveBidResultWithId(bidResultModal, bidResultData)
      } else {
        // 외부 API에서 불러온 데이터 (id 없음)
        await saveBidResultWithBidNo(bidResultData)
      }

      setBidResultModal(null)
      setBidResultData(null)
    } catch (error) {
      // 에러는 각 저장 함수에서 이미 처리됨
    } finally {
      setSavingResult(false)
    }
  }

  const processBiddingNoticeDocuments = async (files) => {
    setIsProcessing(true)
    try {
      console.log(BidApiService)

      console.log(files)
      const urls = files.flatMap((doc) => doc.specDocUrls || [])
      console.log(urls)

      const response = await BidApiService.processBiddingNoticeDocuments(urls)
      console.log("첨부파일 처리 성공:", response)
    } catch (error) {
      console.error("첨부파일 처리 실패:", error)
    } finally {
      setIsProcessing(false)
    }
  }

  return (
    <div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {items.map((item) => {
          const itemKey = `${item.bidNtceNo}-${item.listOrder}`
          const isSaving = savingItems.has(itemKey)
          const isLoadingResult = resultItems.has(itemKey)
          const showBidResultBtn = isOpengDatePassed(item.schedule?.opengDt)

          return (
            <div key={itemKey} className="border rounded-lg p-4 shadow hover:bg-gray-50 transition">
              <div className="flex justify-between items-start mb-2">
                <h2 className="text-blue-600 font-bold text-lg cursor-pointer hover:underline flex-1" onClick={() => setSelected(item)}>
                  #{item.listOrder || item.id}. {item.bidNtceNm}
                </h2>
                <div className="flex gap-2">
                  <button
                    onClick={() => saveItem(item)}
                    disabled={isSaving}
                    className={`px-3 py-1 text-sm rounded transition-colors ${isSaving ? "bg-gray-400 text-white cursor-not-allowed" : "bg-green-600 text-white hover:bg-green-700"}`}
                  >
                    {isSaving ? "저장중..." : "DB저장"}
                  </button>

                  <button
                    onClick={() => processBiddingNoticeDocuments(item.documents)}
                    disabled={isProcessing}
                    className={`px-3 py-1 text-sm rounded transition-colors ${isProcessing ? "bg-gray-400 text-white cursor-not-allowed" : "bg-blue-600 text-white hover:bg-blue-700"}`}
                  >
                    {isProcessing ? "처리중..." : "첨부파일 처리"}
                  </button>

                  {showBidResultBtn && (
                    <button
                      onClick={() => getBidResult(item)}
                      disabled={isLoadingResult}
                      className={`px-3 py-1 text-sm rounded transition-colors ${isLoadingResult ? "bg-gray-400 text-white cursor-not-allowed" : "bg-purple-600 text-white hover:bg-purple-700"}`}
                    >
                      {isLoadingResult ? "로딩중..." : "개찰결과"}
                    </button>
                  )}
                </div>
              </div>

              <p>
                <span className="font-semibold text-gray-700">기관:</span> {item.agencyInfo?.ntceInsttNm}
              </p>
              <p>
                <span className="font-semibold text-gray-700">참가 가능 지역:</span> {item.prtcptPsblRgnNm}({item.prtcptPsblRgnCd})
              </p>
              <p>
                <span className="font-semibold text-gray-700">공고번호:</span> {item.bidNtceNo}
              </p>
              <p>
                <span className="font-semibold text-gray-700">공고일:</span> {formatDate(item.schedule?.bidNtceDt)}
              </p>
              <p>
                <span className="text-green-700">입찰:</span> {formatDate(item.schedule?.bidBeginDt)} ~ {formatDate(item.schedule?.bidClseDt)}
              </p>
              <p>
                <span className="text-purple-700">개찰:</span> {formatDate(item.schedule?.opengDt)}
                {showBidResultBtn && <span className="ml-2 text-xs bg-red-100 text-red-700 px-2 py-1 rounded">개찰완료</span>}
              </p>
              <p>
                <span className="text-red-500">기초금액:</span> {item.budgetInfo?.asignBdgtAmt ? parseInt(item.budgetInfo.asignBdgtAmt).toLocaleString() : "-"}원
              </p>
              <p>
                <span className="text-red-700">추정가격:</span> {item.budgetInfo?.presmptPrce ? parseInt(item.budgetInfo.presmptPrce).toLocaleString() : "-"}원
              </p>
              {item.documents?.[0]?.bidNtceDtlUrl && (
                <a href={item.documents[0].bidNtceDtlUrl} target="_blank" rel="noopener noreferrer" className="text-blue-500 underline mt-2 inline-block">
                  나라장터 상세보기
                </a>
              )}
            </div>
          )
        })}
      </div>

      {/* 페이징 */}
      <div className="flex justify-center mt-6 gap-1 flex-wrap">
        <button onClick={goToPrevGroup} disabled={currentGroup === 0} className="px-3 py-1 border rounded bg-gray-100 hover:bg-gray-200">
          ◀
        </button>
        {pageNumbers.map((page) => (
          <button key={page} onClick={() => goToPage(page)} className={`px-3 py-1 border rounded ${page === currentPage ? "bg-blue-600 text-white" : "bg-gray-100 hover:bg-gray-200"}`}>
            {page}
          </button>
        ))}
        <button onClick={goToNextGroup} disabled={currentGroup === totalPageGroups - 1} className="px-3 py-1 border rounded bg-gray-100 hover:bg-gray-200">
          ▶
        </button>
      </div>

      {/* 상세 모달 */}
      {selected && (
        <Modal
          isOpen={true}
          onRequestClose={() => setSelected(null)}
          className="bg-white p-6 rounded shadow-xl max-w-xl mx-auto mt-10 outline-none"
          overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex items-start justify-center z-50"
        >
          <h2 className="text-xl font-bold mb-4">{selected.bidNtceNm}</h2>
          <p>
            <strong>기관:</strong> {selected.agencyInfo?.ntceInsttNm}
          </p>
          <p>
            <strong>발주처:</strong> {selected.rgstTyNm}
          </p>
          <p>
            <strong>계약방법:</strong> {selected.cntrctCnclsMthdNm}
          </p>
          <p>
            <strong>서비스분류:</strong> {selected.categoryInfo?.pubPrcrmntClsfcNm}
          </p>
          <p>
            <strong>담당자:</strong> {selected.personInCharge?.ntceInsttOfclNm} ({selected.personInCharge?.ntceInsttOfclTelNo})
          </p>
          <p>
            <strong>낙찰하한율:</strong> {selected.applicationCondition?.sucsfbidLwltRate || "-"}%
          </p>
          <p>
            <strong>기초금액:</strong> {selected.budgetInfo?.asignBdgtAmt ? parseInt(selected.budgetInfo.asignBdgtAmt).toLocaleString() : "-"}원
          </p>
          <p>
            <strong>추정가격:</strong> {selected.budgetInfo?.presmptPrce ? parseInt(selected.budgetInfo.presmptPrce).toLocaleString() : "-"}원
          </p>
          <p className="mt-2">
            <strong>첨부파일:</strong>
          </p>
          {selected.documents?.[0]?.specDocUrls?.map((url, i) => {
            const fileName = selected.documents[0]?.specFileNames?.[i] || `파일 ${i + 1}`
            return (
              <a key={i} href={url} target="_blank" rel="noopener noreferrer" className="block text-blue-500 underline">
                📄 {fileName}
              </a>
            )
          })}
          {selected.documents?.[0]?.stdNtceDocUrl && (
            <a href={selected.documents[0].stdNtceDocUrl} target="_blank" rel="noopener noreferrer" className="block text-blue-500 underline">
              📄 표준공고서
            </a>
          )}
          <div className="mt-4 flex justify-end">
            <button onClick={() => setSelected(null)} className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
              닫기
            </button>
          </div>
        </Modal>
      )}

      {/* 개찰결과 모달 */}
      {bidResultModal && (
        <Modal
          isOpen={true}
          onRequestClose={() => {
            setBidResultModal(null)
            setBidResultData(null)
          }}
          className="bg-white p-6 rounded shadow-xl max-w-2xl mx-auto mt-10 outline-none max-h-[80vh] overflow-y-auto"
          overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex items-start justify-center z-50"
        >
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-bold">개찰결과</h2>
            <button
              onClick={() => {
                setBidResultModal(null)
                setBidResultData(null)
              }}
              className="text-gray-500 hover:text-gray-700 text-2xl"
            >
              ×
            </button>
          </div>

          {!bidResultData ? (
            <div className="flex justify-center items-center py-8">
              <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div>
              <span className="ml-3">개찰결과를 불러오는 중...</span>
            </div>
          ) : (
            <div className="space-y-3">
              <h3 className="text-lg font-semibold text-blue-700">{bidResultData.bidNtceNm}</h3>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <p>
                    <strong>공고번호:</strong> {bidResultData.bidNtceNo}
                  </p>
                  <p>
                    <strong>개찰일시:</strong> {formatDate(bidResultData.opengDt)}
                  </p>
                  <p>
                    <strong>참가업체수:</strong> {bidResultData.prtcptCnum}개
                  </p>
                  <p>
                    <strong>진행상태:</strong> {bidResultData.progrsDivCdNm}
                  </p>
                </div>

                <div>
                  <p>
                    <strong>발주기관:</strong> {bidResultData.ntceInsttNm}
                  </p>
                  <p>
                    <strong>수요기관:</strong> {bidResultData.dminsttNm}
                  </p>
                  <p>
                    <strong>예가파일존재:</strong> {bidResultData.rsrvtnPrceFileExistnceYn === "Y" ? "있음" : "없음"}
                  </p>
                  <p>
                    <strong>입력일시:</strong> {formatDate(bidResultData.inptDt)}
                  </p>
                </div>
              </div>

              <div className="border-t pt-4 mt-4">
                <h4 className="text-md font-semibold text-green-700 mb-2">낙찰정보</h4>
                <div className="bg-green-50 p-3 rounded">
                  <p>
                    <strong>낙찰업체:</strong> {bidResultData.bidwinnrNm || "정보없음"}
                  </p>
                  <p>
                    <strong>사업자번호:</strong> {bidResultData.bidwinnrBizno || "정보없음"}
                  </p>
                  <p>
                    <strong>대표자명:</strong> {bidResultData.bidwinnrCeoNm || "정보없음"}
                  </p>
                  <p>
                    <strong>낙찰금액:</strong> {bidResultData.sucsfbidAmt && bidResultData.sucsfbidAmt !== "-" ? parseInt(bidResultData.sucsfbidAmt).toLocaleString() + "원" : "정보없음"}
                  </p>
                  <p>
                    <strong>낙찰률:</strong> {bidResultData.sucsfbidRate && bidResultData.sucsfbidRate !== "-" ? bidResultData.sucsfbidRate + "%" : "정보없음"}
                  </p>
                </div>
              </div>

              {bidResultData.opengRsltNtcCntnts && (
                <div className="border-t pt-4 mt-4">
                  <h4 className="text-md font-semibold mb-2">개찰결과 내용</h4>
                  <div className="bg-gray-50 p-3 rounded max-h-32 overflow-y-auto text-sm">{bidResultData.opengRsltNtcCntnts}</div>
                </div>
              )}

              <div className="flex justify-end gap-3 mt-6 pt-4 border-t">
                <button
                  onClick={() => {
                    setBidResultModal(null)
                    setBidResultData(null)
                  }}
                  className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
                  disabled={savingResult}
                >
                  닫기
                </button>
                <button
                  onClick={handleSaveBidResult}
                  disabled={savingResult}
                  className={`px-4 py-2 rounded text-white ${savingResult ? "bg-gray-400 cursor-not-allowed" : "bg-blue-600 hover:bg-blue-700"}`}
                >
                  {savingResult ? "저장중..." : "저장"}
                </button>
              </div>
            </div>
          )}
        </Modal>
      )}
    </div>
  )
}

export default BidList
