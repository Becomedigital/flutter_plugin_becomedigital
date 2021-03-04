import 'package:meta/meta.dart';

class BecomeDigitalConfig {
  final String clientId;
  final String clientSecret;
  final String contractId;
  final String validatiopnTypes;
  final bool useGallery;
  BecomeDigitalConfig({
    @required this.clientId,
    @required this.clientSecret,
    @required this.contractId,
    @required this.validatiopnTypes,
    @required this.useGallery,
  });


  Map<String,dynamic> toMap(){
    return {
      'clientId':clientId
    };
  }
}
