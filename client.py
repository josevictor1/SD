import socket

s = socket.socket()
host = "localhost"
port  = 12345

s.connect((host, port))
print s.recv(1024)
while True:
    message = raw_input()
    s.send(message)
    recived = s.recv(1024)
    print recived
    if recived == "OK!":
        s.close()
        break
