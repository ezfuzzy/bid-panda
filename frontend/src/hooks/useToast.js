import { useState } from "react"
import { BID_SEARCH_CONSTANTS } from "constants/mapping"

export const useToast = () => {
  const [toast, setToast] = useState({
    show: false,
    message: "",
    type: "success",
  })

  const showToast = (message, type = "success") => {
    setToast({ show: true, message, type })
    setTimeout(() => {
      setToast({ show: false, message: "", type: "success" })
    }, BID_SEARCH_CONSTANTS.TOAST_AUTO_CLOSE_TIME)
  }

  const hideToast = () => {
    setToast({ show: false, message: "", type: "success" })
  }

  return { toast, showToast, hideToast }
}
