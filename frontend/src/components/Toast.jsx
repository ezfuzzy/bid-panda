import React from "react"

const Toast = ({ toast, onClose }) => {
  if (!toast.show) return null

  const getToastStyle = () => {
    switch (toast.type) {
      case "success":
        return "bg-green-500 text-white"
      case "error":
        return "bg-red-500 text-white"
      case "warning":
        return "bg-yellow-500 text-white"
      default:
        return "bg-blue-500 text-white"
    }
  }

  const getIcon = () => {
    switch (toast.type) {
      case "success":
        return "✅"
      case "error":
        return "❌"
      case "warning":
        return "⚠️"
      default:
        return "ℹ️"
    }
  }

  return (
    <div className="fixed top-4 right-4 z-50 animate-in slide-in-from-top-2 duration-300">
      <div className={`${getToastStyle()} px-6 py-4 rounded-lg shadow-lg max-w-md flex items-center`}>
        <span className="mr-3 text-lg">{getIcon()}</span>
        <div>
          <p className="font-medium">{toast.message}</p>
        </div>
        <button onClick={onClose} className="ml-4 text-white hover:text-gray-200 transition-colors">
          ✕
        </button>
      </div>
    </div>
  )
}

export default Toast
