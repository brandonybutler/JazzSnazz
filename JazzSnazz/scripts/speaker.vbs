Dim message, sapi
Set args = WScript.Arguments

' Check that all arguments have been specified and raise an error otherwise
If WScript.Arguments.Count > 1 Or WScript.Arguments.Count < 1 Then
  Err.Raise 450
End If

' Say the message
message = WScript.Arguments.Item(0)
Set sapi = CreateObject("sapi.spvoice")
sapi.Volume = 100
sapi.Speak message
