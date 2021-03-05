class BecomeError {
  final String message;

  BecomeError(this.message);

  static BecomeError fromMap(Map<dynamic, dynamic> map) {
    return BecomeError(map['message']);
  }
}
