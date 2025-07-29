import React, { useState } from "react"
import { BidApiService } from "../bidApiService"

const ApiTestSection = ({ fetchDataFromDB, showToast, setData, setCurrentData }) => {
  const [inputId, setInputId] = useState("")
  const [loading, setLoading] = useState({
    fetch: false,
    delete: false,
  })

  const handleApiTest = async () => {
    try {
      const res = await BidApiService.testApi()
      console.log("API 테스트 결과:", res)
      showToast("API 테스트 성공", "success")
    } catch (err) {
      console.error("API 테스트 실패:", err)
      showToast("API 테스트 실패", "error")
    }
  }

  const handleFetchById = async () => {
    if (!inputId.trim()) {
      showToast("ID를 입력해주세요", "warning")
      return
    }

    setLoading((prev) => ({ ...prev, fetch: true }))
    try {
      const res = await BidApiService.fetchById(inputId)
      console.log(`ID ${inputId} 데이터:`, res.data)
      showToast(`ID ${inputId} 데이터를 성공적으로 가져왔습니다`, "success")
    } catch (err) {
      console.error(`ID ${inputId} 조회 실패:`, err)
      if (err.response?.status === 404) {
        showToast(`ID ${inputId}에 해당하는 데이터를 찾을 수 없습니다`, "error")
      } else {
        showToast(`ID ${inputId} 조회 중 오류가 발생했습니다`, "error")
      }
    } finally {
      setLoading((prev) => ({ ...prev, fetch: false }))
    }
  }

  const handleDeleteById = async () => {
    if (!inputId.trim()) {
      showToast("삭제할 ID를 입력해주세요", "warning")
      return
    }

    const confirmDelete = window.confirm(`정말로 ID ${inputId}번 데이터를 삭제하시겠습니까?\n\n이 작업은 되돌릴 수 없습니다.`)

    if (!confirmDelete) {
      return
    }

    setLoading((prev) => ({ ...prev, delete: true }))
    try {
      const res = await BidApiService.deleteById(inputId)
      console.log(`ID ${inputId} 삭제 결과:`, res)
      showToast(`ID ${inputId} 데이터가 성공적으로 삭제되었습니다`, "success")

      // setData((prev) => {
      //   prev.filter((item) => item.id !== inputId)
      // })
      setCurrentData((prev) => {
        prev.filter((item) => item.id !== inputId)
      })

      setInputId("") // 삭제 성공 시 입력 필드 초기화
    } catch (err) {
      console.error(`ID ${inputId} 삭제 실패:`, err)
      if (err.response?.status === 404) {
        showToast(`ID ${inputId}에 해당하는 데이터를 찾을 수 없습니다`, "error")
      } else {
        showToast(`ID ${inputId} 삭제 중 오류가 발생했습니다`, "error")
      }
    } finally {
      setLoading((prev) => ({ ...prev, delete: false }))
    }
  }

  const handleBackendApiTest = async () => {
    try {
      const res = await BidApiService.testBackendApi()
      console.log("백엔드 API 테스트 결과:", res)
      showToast("백엔드 API 테스트 성공", "success")
    } catch (err) {
      console.error("백엔드 API 테스트 실패:", err)
      showToast("백엔드 API 테스트 실패", "error")
    }
  }

  const handleDeleteAll = async () => {
    // 삭제 확인
    const confirmDelete = window.confirm(`정말로 모든 데이터를 삭제하시겠습니까?\n\n이 작업은 되돌릴 수 없습니다.`)

    if (!confirmDelete) {
      return
    }

    setLoading((prev) => ({ ...prev, delete: true }))
    try {
      const res = await BidApiService.deleteAll()
      console.log(` 삭제 결과:`, res)
      showToast(`모든 데이터가 성공적으로 삭제되었습니다`, "success")
      setInputId("") // 삭제 성공 시 입력 필드 초기화
    } catch (err) {
      console.error(`삭제 실패:`, err)
      if (err.response?.status === 404) {
        showToast(`데이터가 없습니다`, "error")
      } else {
        showToast(`삭제 중 오류가 발생했습니다`, "error")
      }
    } finally {
      setLoading((prev) => ({ ...prev, delete: false }))
    }
  }

  return (
    <div className="bg-gray-50 rounded-lg p-4 space-y-4">
      <h3 className="text-sm font-medium text-gray-700 mb-3">🛠️ API 테스트 도구</h3>

      {/* 첫 번째 줄: 기본 API 테스트 */}
      <div className="flex flex-wrap gap-2">
        <button
          onClick={handleApiTest}
          className="px-4 py-2 bg-green-100 text-green-700 font-medium rounded-lg hover:bg-green-200 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2 transition-all text-sm">
          API TEST
        </button>

        <button
          onClick={() => fetchDataFromDB(-1)}
          className="px-4 py-2 bg-purple-100 text-purple-700 font-medium rounded-lg hover:bg-purple-200 focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-offset-2 transition-all text-sm">
          DB에서 데이터 가져오기
        </button>

        <button
          onClick={handleBackendApiTest}
          className="px-4 py-2 bg-indigo-100 text-indigo-700 font-medium rounded-lg hover:bg-indigo-200 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition-all text-sm">
          백엔드 API 테스트
        </button>

        <button
          onClick={handleDeleteAll}
          disabled={loading.delete}
          className="px-4 py-2 bg-red-100 text-red-700 font-medium rounded-lg hover:bg-red-200 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 transition-all disabled:opacity-50 disabled:cursor-not-allowed text-sm">
          DB 전체 삭제
        </button>
      </div>

      {/* 두 번째 줄: ID 기반 작업 */}
      <div className="flex flex-wrap items-center gap-2">
        <div className="flex items-center gap-2">
          <label htmlFor="apiTestId" className="text-sm font-medium text-gray-600 whitespace-nowrap">
            데이터 ID:
          </label>
          <input
            id="apiTestId"
            type="number"
            value={inputId}
            onChange={(e) => setInputId(e.target.value)}
            placeholder="예: 1"
            className="w-20 px-2 py-1 text-sm border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
            min="1"
          />
        </div>

        <button
          onClick={handleFetchById}
          disabled={loading.fetch}
          className="px-4 py-2 bg-yellow-100 text-yellow-700 font-medium rounded-lg hover:bg-yellow-200 focus:outline-none focus:ring-2 focus:ring-yellow-500 focus:ring-offset-2 transition-all disabled:opacity-50 disabled:cursor-not-allowed text-sm">
          {loading.fetch ? (
            <span className="flex items-center">
              <svg className="animate-spin -ml-1 mr-1 h-3 w-3 text-yellow-700" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path
                  className="opacity-75"
                  fill="currentColor"
                  d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              조회중...
            </span>
          ) : (
            "📄 데이터 조회"
          )}
        </button>

        <button
          onClick={handleDeleteById}
          disabled={loading.delete}
          className="px-4 py-2 bg-red-100 text-red-700 font-medium rounded-lg hover:bg-red-200 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 transition-all disabled:opacity-50 disabled:cursor-not-allowed text-sm">
          {loading.delete ? (
            <span className="flex items-center">
              <svg className="animate-spin -ml-1 mr-1 h-3 w-3 text-red-700" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path
                  className="opacity-75"
                  fill="currentColor"
                  d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              삭제중...
            </span>
          ) : (
            "🗑️ 데이터 삭제"
          )}
        </button>
      </div>

      <div className="text-xs text-gray-500 mt-2">💡 팁: 데이터 조회/삭제 전에 ID를 입력하세요. 삭제는 확인 후 실행됩니다.</div>
    </div>
  )
}

export default ApiTestSection
