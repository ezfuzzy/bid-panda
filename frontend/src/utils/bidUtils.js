import { BID_SEARCH_CONSTANTS, regionOptions, industryOptions } from "constants/mapping"

// 지역코드로 지역명 찾기
export const getRegionNameByCode = (code) => {
  const matchedRegion = regionOptions.find((region) => region.code === code)
  return matchedRegion ? matchedRegion.name : null
}

// 업종코드로 업종명 찾기기
export const getIndustryNameByCode = (code) => {
  const matchedIndustry = industryOptions.find((industry) => industry.code === code)
  return matchedIndustry ? matchedIndustry.name : null
}

// 데이터를 텍스트 형식으로 포맷팅
export const formatDataToText = (data, indstrytyCd) => {
  return data
    .map((item) => {
      return `${item.bidNtceNm ?? "-"}\t${item.rgstTyNm ?? "-"}\t${item.bidClseDt ?? "-"}\t${item.sucsfbidLwltRate ?? "-"}\t${indstrytyCd ?? "-"}\t${
        item.prtcptPsblRgnNm ?? "-"
      }\t${((item.sucsfbidLwltRate ?? 0) / 100).toFixed(5)}\t${item.asignBdgtAmt ?? "-"}`
    })
    .join("\n")
}

// 파일 다운로드
export const downloadFile = (data, indstrytyCd, filename = "BitList.txt") => {
  const formattedText = formatDataToText(data, indstrytyCd)
  const blob = new Blob([formattedText], { type: "text/plain;charset=utf-8" })
  const url = window.URL.createObjectURL(blob)

  const a = document.createElement("a")
  a.href = url
  a.download = filename
  a.click()

  window.URL.revokeObjectURL(url)
}

// 클립보드에 복사
export const copyToClipboard = async (data, indstrytyCd) => {
  const formattedText = formatDataToText(data, indstrytyCd)

  try {
    await navigator.clipboard.writeText(formattedText)
    alert(BID_SEARCH_CONSTANTS.MESSAGES.COPY_SUCCESS)
  } catch (err) {
    alert(BID_SEARCH_CONSTANTS.MESSAGES.COPY_FAILED + err)
  }
}

// 마감 임박 필터링 (3일 이내)
export const filterUrgentBids = (data) => {
  const now = new Date()
  const threeDaysLaterEnd = new Date(now)
  threeDaysLaterEnd.setDate(now.getDate() + BID_SEARCH_CONSTANTS.DATE.URGENT_DEADLINE_DAYS)
  threeDaysLaterEnd.setHours(23, 59, 0, 0)

  return data.filter((item) => {
    if (!item.bidClseDt) return false
    const deadline = new Date(item.bidClseDt)
    return deadline >= now && deadline <= threeDaysLaterEnd
  })
}

// 공고 종류 및 키워드 필터링
// TODO: -> 만약 같은 이름이나 같은 공고번호인 경우 재등록 차수가 높은거만 유효. 나머진 필터링

export const filterBidItems = (items, excludeKeyword) => {
  // 취소/연기 공고 필터링 + 키워드 필터링링
  const filteredItems = items.filter(
    (item) => !BID_SEARCH_CONSTANTS.EXCLUDE_NOTICE_TYPES.includes(item.ntceKindNm) && (!excludeKeyword || !item.bidNtceNm?.includes(excludeKeyword))
  )

  // bidNtceNo별로 그룹화하여 bidNtceOrd가 가장 큰 항목만 남기기
  const uniqueItems = filteredItems.reduce((acc, item) => {
    const existingItem = acc.find((accItem) => accItem.bidNtceNo === item.bidNtceNo)

    if (!existingItem) {
      // 해당 bidNtceNo가 처음 나온 경우
      acc.push(item)
    } else if (item.bidNtceOrd > existingItem.bidNtceOrd) {
      // 더 큰 bidNtceOrd를 가진 항목으로 교체
      const index = acc.indexOf(existingItem)
      acc[index] = item
    }

    return acc
  }, [])

  return uniqueItems
}

// 페이지네이션 계산
export const calculatePagination = (totalCount, currentPage, rowsPerPage = BID_SEARCH_CONSTANTS.PAGINATION.ROWS_PER_PAGE) => {
  const totalPages = Math.ceil(totalCount / rowsPerPage)
  const startIdx = (currentPage - 1) * rowsPerPage
  const endIdx = startIdx + rowsPerPage

  return { totalPages, startIdx, endIdx }
}

// 이전 기간 날짜 계산
export const calculatePreviousPeriod = (startDate) => {
  const newStartDate = new Date(startDate)
  newStartDate.setDate(newStartDate.getDate() - BID_SEARCH_CONSTANTS.DATE.PREVIOUS_PERIOD_DAYS)
  newStartDate.setHours(0, 0, 0, 0)

  const newEndDate = new Date(startDate)
  newEndDate.setDate(startDate.getDate() - 1)
  newEndDate.setHours(23, 59, 0, 0)

  return { newStartDate, newEndDate }
}
