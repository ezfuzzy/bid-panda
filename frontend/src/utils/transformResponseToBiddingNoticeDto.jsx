/**
 * 외부 API 응답(플랫 JSON)을 백엔드 BiddingNoticeDto 구조로 변환하는 함수입니다.
 * @param {object} flatResponse 외부 API에서 받은 플랫 JSON 객체
 * @returns {object} BiddingNoticeDto 객체
 */
export const transformResponseToBiddingNoticeDto = (flatResponse) => {
  // 헬퍼 함수들
  const toBoolean = (val) => {
    if (val === undefined || val === null) return undefined
    return val.toUpperCase() === "Y"
  }

  const toLocalDateTime = (dateStr) => {
    if (!dateStr) return undefined
    // 'YYYY-MM-DD HH:MM:SS' 형식을 'YYYY-MM-DDTHH:MM:SS'로 변환
    return dateStr.replace(" ", "T")
  }

  const toBigDecimal = (val) => {
    if (!val) return undefined
    return parseFloat(val)
  }

  const toInteger = (val) => {
    if (!val) return undefined
    return parseInt(val, 10)
  }

  // BiddingDocumentInfo의 specDocUrls와 specFileNames를 수집
  const specDocUrls = []
  const specFileNames = []
  for (let i = 1; i <= 10; i++) {
    const url = flatResponse[`ntceSpecDocUrl${i}`]
    const fileName = flatResponse[`ntceSpecFileNm${i}`]
    if (url) specDocUrls.push(url)
    if (fileName) specFileNames.push(fileName)
  }

  // BiddingDocumentInfoDto 객체 생성 (단일 문서 정보)
  const documentInfo = {
    specDocUrls: specDocUrls.length > 0 ? specDocUrls : undefined,
    specFileNames: specFileNames.length > 0 ? specFileNames : undefined,
    stdNtceDocUrl: flatResponse.stdNtceDocUrl || undefined,
    dcmtgOprtnPlce: flatResponse.dcmtgOprtnPlce || undefined,
    bidNtceDtlUrl: flatResponse.bidNtceDtlUrl || undefined,
    bidNtceUrl: flatResponse.bidNtceUrl || undefined,
  }

  // BiddingResultDto (생성 시에는 null로 설정)
  const biddingResult = null // 생성 시에는 null

  return {
    // BiddingNotice 기본 필드 매핑
    bidNtceNo: flatResponse.bidNtceNo || undefined,
    bidNtceOrd: flatResponse.bidNtceOrd || undefined,
    bidNtceNm: flatResponse.bidNtceNm || undefined,
    ntceKindNm: flatResponse.ntceKindNm || undefined,
    rgstTyNm: flatResponse.rgstTyNm || undefined,

    reNtceYn: toBoolean(flatResponse.reNtceYn),
    intrbidYn: toBoolean(flatResponse.intrbidYn),

    bidMethdNm: flatResponse.bidMethdNm || undefined,
    cntrctCnclsMthdNm: flatResponse.cntrctCnclsMthdNm || undefined,
    refNo: flatResponse.refNo || undefined,
    untyNtceNo: flatResponse.untyNtceNo || undefined,

    indstrytyCd: flatResponse.indstrytyCd || undefined,
    indstrytyNm: flatResponse.indstrytyNm || undefined,
    prtcptPsblRgnCd: flatResponse.prtcptPsblRgnCd || undefined,
    prtcptPsblRgnNm: flatResponse.prtcptPsblRgnNm || undefined,

    agencyInfo: {
      ntceInsttCd: flatResponse.ntceInsttCd || undefined,
      ntceInsttNm: flatResponse.ntceInsttNm || undefined,
      dminsttCd: flatResponse.dminsttCd || undefined,
      dminsttNm: flatResponse.dminsttNm || undefined,
    },

    personInCharge: {
      ntceInsttOfclNm: flatResponse.ntceInsttOfclNm || undefined,
      ntceInsttOfclTelNo: flatResponse.ntceInsttOfclTelNo || undefined,
      ntceInsttOfclEmailAdrs: flatResponse.ntceInsttOfclEmailAdrs || undefined,
      exctvNm: flatResponse.exctvNm || undefined,
    },

    schedule: {
      bidNtceDt: toLocalDateTime(flatResponse.bidNtceDt),
      bidBeginDt: toLocalDateTime(flatResponse.bidBeginDt),
      bidClseDt: toLocalDateTime(flatResponse.bidClseDt),
      opengDt: toLocalDateTime(flatResponse.opengDt),
      bidQlfctRgstDt: toLocalDateTime(flatResponse.bidQlfctRgstDt),
      pqApplDocRcptDt: toLocalDateTime(flatResponse.pqApplDocRcptDt),
      tpEvalApplClseDt: toLocalDateTime(flatResponse.tpEvalApplClseDt),
      arsltReqstdocRcptDt: toLocalDateTime(flatResponse.arsltReqstdocRcptDt),
      dcmtgOprtnDt: toLocalDateTime(flatResponse.dcmtgOprtnDt),
      rbidOpengDt: toLocalDateTime(flatResponse.rbidOpengDt),
      rgstDt: toLocalDateTime(flatResponse.rgstDt),
      chgDt: toLocalDateTime(flatResponse.chgDt),
    },

    documents: [documentInfo], // 단일 BiddingDocumentInfoDto 객체를 리스트에 담음

    conditions: {
      cmmnSpldmdAgrmntRcptdocMethd: flatResponse.cmmnSpldmdAgrmntRcptdocMethd || undefined,
      cmmnSpldmdAgrmntClseDt: toLocalDateTime(flatResponse.cmmnSpldmdAgrmntClseDt),
      cmmnSpldmdCorpRgnLmtYn: toBoolean(flatResponse.cmmnSpldmdCorpRgnLmtYn),
      rbidPermsnYn: toBoolean(flatResponse.rbidPermsnYn),
      bidPrtcptLmtYn: toBoolean(flatResponse.bidPrtcptLmtYn),
      indstrytyLmtYn: toBoolean(flatResponse.indstrytyLmtYn),
      dtlsBidYn: toBoolean(flatResponse.dtlsBidYn),
      brffcBidprcPermsnYn: toBoolean(flatResponse.brffcBidprcPermsnYn),
      dsgntCmptYn: toBoolean(flatResponse.dsgntCmptYn),
      arsltCmptYn: toBoolean(flatResponse.arsltCmptYn),
      pqEvalYn: toBoolean(flatResponse.pqEvalYn),
      tpEvalYn: toBoolean(flatResponse.tpEvalYn),
      ntceDscrptYn: toBoolean(flatResponse.ntceDscrptYn),
      rsrvtnPrceReMkngMthdNm: flatResponse.rsrvtnPrceReMkngMthdNm || undefined,
      arsltApplDocRcptMthdNm: flatResponse.arsltApplDocRcptMthdNm || undefined,
      arsltReqstdocRcptDt: toLocalDateTime(flatResponse.arsltReqstdocRcptDt),
    },

    budgetInfo: {
      asignBdgtAmt: toBigDecimal(flatResponse.asignBdgtAmt),
      presmptPrce: toBigDecimal(flatResponse.presmptPrce),
      prearngPrceDcsnMthdNm: flatResponse.prearngPrceDcsnMthdNm || undefined,
      totPrdprcNum: toInteger(flatResponse.totPrdprcNum),
      drwtPrdprcNum: toInteger(flatResponse.drwtPrdprcNum),
      bidPrtcptFee: toBigDecimal(flatResponse.bidPrtcptFee),
      VAT: toBigDecimal(flatResponse.VAT),
      indutyVAT: toBigDecimal(flatResponse.indutyVAT),
    },

    categoryInfo: {
      ppswGnrlSrvceYn: toBoolean(flatResponse.ppswGnrlSrvceYn),
      srvceDivNm: flatResponse.srvceDivNm || undefined,
      prdctClsfcLmtYn: toBoolean(flatResponse.prdctClsfcLmtYn),
      mnfctYn: toBoolean(flatResponse.mnfctYn),
      purchsObjPrdctList: flatResponse.purchsObjPrdctList || undefined,
      pubPrcrmntLrgclsfcNm: flatResponse.pubPrcrmntLrgClsfcNm || undefined,
      pubPrcrmntMidclsfcNm: flatResponse.pubPrcrmntMidClsfcNm || undefined,
      pubPrcrmntClsfcNo: flatResponse.pubPrcrmntClsfcNo || undefined,
      pubPrcrmntClsfcNm: flatResponse.pubPrcrmntClsfcNm || undefined,
    },

    applicationCondition: {
      pqApplDocRcptMthdNm: flatResponse.pqApplDocRcptMthdNm || undefined,
      tpEvalApplMthdNm: flatResponse.tpEvalApplMthdNm || undefined,
      rsrvtnPrceReMkngMthdNm: flatResponse.rsrvtnPrceReMkngMthdNm || undefined,
      arsltApplDocRcptMthdNm: flatResponse.arsltApplDocRcptMthdNm || undefined,
      cmmnSpldmdMethdCd: flatResponse.cmmnSpldmdMethdCd || undefined,
      cmmnSpldmdMethdNm: flatResponse.cmmnSpldmdMethdNm || undefined,
      jntcontrctDutyRgnNm1: flatResponse.jntcontrctDutyRgnNm1 || undefined,
      jntcontrctDutyRgnNm2: flatResponse.jntcontrctDutyRgnNm2 || undefined,
      jntcontrctDutyRgnNm3: flatResponse.jntcontrctDutyRgnNm3 || undefined,
      rgnDutyJntcontrctRt: toBigDecimal(flatResponse.rgnDutyJntcontrctRt),
      sucsfbidLwltRate: toBigDecimal(flatResponse.sucsfbidLwltRate),
      sucsfbidMthdCd: flatResponse.sucsfbidMthdCd || undefined,
      sucsfbidMthdNm: flatResponse.sucsfbidMthdNm || undefined,
    },

    changeHistory: {
      chgDt: toLocalDateTime(flatResponse.chgDt),
      chgNtceRsn: flatResponse.chgNtceRsn || undefined,
    },

    result: biddingResult,
  }
}
