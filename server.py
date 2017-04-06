import socket
s = socket.socket()
host = "localhost"
port = 12345
s.bind((host,port))
s.listen(5)
while True:
	c, addr = s.accept()
	print 'got connection from', addr
	c.send('Obrigado pela conexao')
	recived = c.recv(1024)
	print recived
	if recived != " ":
		message = "OK!"
		print "OK! The end!"
		c.send("OK!")
		c.close()
		break
