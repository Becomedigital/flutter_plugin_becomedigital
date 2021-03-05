class ResponseIv {
  ResponseIv({
    this.id,
    this.createdAt,
    this.company,
    this.fullname,
    this.birth,
    this.documentType,
    this.documentNumber,
    this.faceMatch,
    this.template,
    this.alteration,
    this.watchList,
    this.complyAdvantageResult,
    this.complyAdvantageUrl,
    this.verificationStatus,
    this.message,
    this.responseStatus,
  });

  String id;
  String createdAt;
  String company;
  String fullname;
  String birth;
  String documentType;
  String documentNumber;
  bool faceMatch;
  bool template;
  bool alteration;
  bool watchList;
  String complyAdvantageResult;
  String complyAdvantageUrl;
  String verificationStatus;
  String message;
  int responseStatus;

  factory ResponseIv.fromJson(Map<dynamic, dynamic> json) => ResponseIv(
        id: json["id"],
        createdAt: json["created_at"],
        company: json["company"],
        fullname: json["fullname"],
        birth: json["birth"],
        documentType: json["document_type"],
        documentNumber: json["document_number"],
        faceMatch: json["face_match"],
        template: json["template"],
        alteration: json["alteration"],
        watchList: json["watch_list"],
        complyAdvantageResult: json["comply_advantage_result"],
        complyAdvantageUrl: json["comply_advantage_url"],
        verificationStatus: json["verification_status"],
        message: json["message"],
        responseStatus: json["responseStatus"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "created_at": createdAt,
        "company": company,
        "fullname": fullname,
        "birth": birth,
        "document_type": documentType,
        "document_number": documentNumber,
        "face_match": faceMatch,
        "template": template,
        "alteration": alteration,
        "watch_list": watchList,
        "comply_advantage_result": complyAdvantageResult,
        "comply_advantage_url": complyAdvantageUrl,
        "verification_status": verificationStatus,
        "message": message,
        "responseStatus": responseStatus,
      };

  @override
  String toString() {
    return """
    id:$id
    createdAt:$createdAt
    company:$company
    fullname:$fullname
    birth:$birth
    documentType:$documentType
    documentNumber:$documentNumber
    faceMatch:$faceMatch
    template:$template
    alteration:$alteration
    watchList:$watchList
    complyAdvantageResult:$complyAdvantageResult
    complyAdvantageUrl:$complyAdvantageUrl
    verificationStatus:$verificationStatus
    message:$message
    responseStatus:$responseStatus
    """;
  }
}
