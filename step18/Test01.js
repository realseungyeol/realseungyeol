const net = require("net")

const socket = new net.Socket()


//서버 연결하기/
socket.connect(8888,'localhost', ()=>{
	console.log(" => 연결 성공")
	socket.write("안녕, \n")
	console.log(" => 서버에 데이터 보냈다")
})


//3_ 데이터를 읽었을때 호출될 리스너 등록하기.
//	 서버에서 데이터를 보내면data 이벤트가 발생한다.
//   이 이벤트가 발생하면 등록된 함수가 호출된다.
socket.on("data", function(data) {
	console.log(data.toString())
	socket.destroy()
})
	


//4) 'close' 이벤트가 발생했을떄 호출될 함수를 등록한다.
// => 소켓을 닫으면 서버와의 연결이 끊어 졌음을 알리는'close' 이벤트가 발생한다.
socket.on("close",()=> {
	console.log("연결뚝")
})
