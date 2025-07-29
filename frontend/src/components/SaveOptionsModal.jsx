import React from "react"

const SaveOptionsModal = ({ showSaveOptions, onClose, onSave, currentData, data, saveLoading }) => {
  if (!showSaveOptions) return null

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 max-w-md w-full mx-4">
        <h3 className="text-lg font-semibold mb-4">저장할 데이터를 선택하세요</h3>

        <div className="space-y-3">
          <button
            onClick={() => onSave(currentData, "현재 페이지")}
            className="w-full p-4 bg-blue-50 hover:bg-blue-100 rounded-lg border-2 border-blue-200 hover:border-blue-300 transition-all text-left"
            disabled={saveLoading}>
            <div className="font-medium text-blue-800">현재 페이지만 저장</div>
            <div className="text-sm text-blue-600 mt-1">{currentData?.length || 0}개 항목</div>
          </button>

          <button
            onClick={() => onSave(data, "전체 데이터")}
            className="w-full p-4 bg-green-50 hover:bg-green-100 rounded-lg border-2 border-green-200 hover:border-green-300 transition-all text-left"
            disabled={saveLoading}>
            <div className="font-medium text-green-800">전체 데이터 저장</div>
            <div className="text-sm text-green-600 mt-1">{data?.length || 0}개 항목</div>
          </button>
        </div>

        <div className="flex justify-end mt-6">
          <button onClick={onClose} className="px-4 py-2 text-gray-600 hover:text-gray-800 transition-colors" disabled={saveLoading}>
            취소
          </button>
        </div>
      </div>
    </div>
  )
}

export default SaveOptionsModal
