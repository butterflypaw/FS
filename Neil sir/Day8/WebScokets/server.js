const WebSocket = require("ws");
const server = new WebSocket.Server({port : 8080});
let employees = [];
let id = 1;
server.on("connection", (ws) => {
    server.on("messsage"), (msg) => {
        const inp = msg.toString().split(' ');
        const cmd = inp[0].toUpperCase();
        if(cmd == "INSERT" && inp.length === 3){
            employees.push({ID : id++, Name : inp[1], Salary : parseInt(inp[2])});
            ws.send("Employee inserted successfully.");
        }
        else if(cmd == "RETRIEVE"){
            employees.forEach(e => ws.send(e => `ID: ${e.ID}, Name: ${e.Name}, Salary: ${e.Salary}`));
        }
        else{
            ws.send("Invalid command.");
        }
    }
    // server.on("close", ()=>{
    //     console.log("Connection ended");
    // })
})
// console.log("Client connected");