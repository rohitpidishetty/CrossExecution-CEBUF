import base64

print("Cross-Exe initiated")
file = open("./cross_exe_mem.cebuf", "r")
fobj = file.read()
decoded_bytes = base64.b64decode(fobj)
decoded = decoded_bytes.decode("utf-8")
string = decoded.upper()
content = base64.b64encode(string.encode("utf-8")).decode("utf-8")
file.close()
file = open("./cross_exe_mem.cebuf", "w")
file.write(content)
file.close()
