// BidList.jsx
import React, { useState } from "react"
import Modal from "react-modal"
import axios from "axios"

Modal.setAppElement("#root")

const formatDate = (datetime) => datetime?.split("T")[0]

const BidList = ({ items, currentPage, totalPages, onPageChange, showToast }) => {
  const [selected, setSelected] = useState(null)
  const [savingItems, setSavingItems] = useState(new Set()) // 저장 중인 아이템들 추적
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

  // 개별 아이템 저장 함수
  const saveItem = async (item) => {
    const itemKey = `${item.bidNtceNo}-${item.listOrder}`

    // 이미 저장 중인 경우 중복 요청 방지
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

  return (
    <div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {items.map((item) => {
          const itemKey = `${item.bidNtceNo}-${item.listOrder}`
          const isSaving = savingItems.has(itemKey)

          return (
            <div key={itemKey} className="border rounded-lg p-4 shadow hover:bg-gray-50 transition">
              <div className="flex justify-between items-start mb-2">
                <h2 className="text-blue-600 font-bold text-lg cursor-pointer hover:underline flex-1" onClick={() => setSelected(item)}>
                  #{item.listOrder || item.id}. {item.bidNtceNm}
                </h2>
                <button
                  onClick={() => saveItem(item)}
                  disabled={isSaving}
                  className={`ml-2 px-3 py-1 text-sm rounded transition-colors ${
                    isSaving ? "bg-gray-400 text-white cursor-not-allowed" : "bg-green-600 text-white hover:bg-green-700"
                  }`}>
                  {isSaving ? "저장중..." : "DB저장"}
                </button>
              </div>
              <p>
                <span className="font-semibold text-gray-700">기관:</span> {item.agencyInfo?.ntceInsttNm}
              </p>
              <p>
                <span className="font-semibold text-gray-700">참가 가능 지역:</span> {item.prtcptPsblRgnNm}
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
          <button
            key={page}
            onClick={() => goToPage(page)}
            className={`px-3 py-1 border rounded ${page === currentPage ? "bg-blue-600 text-white" : "bg-gray-100 hover:bg-gray-200"}`}>
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
          overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex items-start justify-center z-50">
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
    </div>
  )
}

export default BidList
