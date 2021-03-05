import 'package:meta/meta.dart';

class BecomeDigitalConfig {
  final String clientId;
  final String userId;
  final String clientSecret;
  final String contractId;
  /// /A/B/C
  final String validatiopnTypes;
  final bool useGallery;
  BecomeDigitalConfig({
    @required this.clientId,
    @required this.clientSecret,
    @required this.contractId,
    @required this.validatiopnTypes,
    @required this.useGallery,
    @required this.userId

  });

  Map<String, dynamic> toMap() {
    return {
      'clientId': clientId,
      'clientSecret': clientSecret,
      'contractId': contractId,
      'validatiopnTypes': validatiopnTypes,
      'useGallery': useGallery,
      'userId': userId,
    };
  }
}
