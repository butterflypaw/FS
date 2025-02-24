// <!--
// Employee Management WebSocket Application with MongoDB

// Objective:
// ----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
// The system should allow multiple clients to interact with a database to perform the following operations:
// 	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
// 	2. Retrieve Employee List (RETRIEVE)
// 	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
// The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// // MongoDB Employee Schema
// const employeeSchema = new mongoose.Schema({
//     name: String,
//     salary: Number,
//     role: String,
//     department: String,
//     experience: Number
// });

// Requirements:
// -------------
// Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections. (give a response as "Connected" )
// 		-> Process incoming commands from clients as discussed above.
// 		-> Log each received command on the console.
// 		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
		
// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			                Server Response
// ============================================================================================
// INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
// INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

// RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

// RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


// INVALID					                "Invalid command."
// ============================================================================================

// Note: 
// -> Your implementation must use MongoDB for data persistence.
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080

// -->
// <config>
//     <url value="ws://10.11.8.15:8081"></url>
// </config>

const mongoose = require('mongoose');
const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8081 });
const AutoIncrement = require("mongoose-sequence")(mongoose);

mongoose.connect("mongodb://127.0.0.1:27017/webSocket").then(() => {
    console.log("Connected to DB");
}).catch((err) => {
    console.log(err);
});


const userSchema = new mongoose.Schema({
    userId: Number,
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number,
});


userSchema.plugin(AutoIncrement, { inc_field: "userId" });

const User = mongoose.model("User", userSchema);

wss.on('connection', (ws) => {
    console.log('Client Connected');

    ws.on('message', async (message) => {
        console.log(`Received: ${message}`);

        const parts = message.toString().split(' ');
        const command = parts[0].toUpperCase();

        if (command === 'INSERT' && parts.length === 6) {

            const name = parts[1];
            const salary = parseInt(parts[2], 10);
            const role = parts[3];
            const department = parts[4];
            const experience = parseInt(parts[5], 10);

            if (!isNaN(salary) && !isNaN(experience)) {
                const newUser = new User({ name, salary, role, department, experience });
                const emp=await newUser.save();
                ws.send(`Employee inserted successfully. ID: ${emp.userId}`);
            } else {
                ws.send('Invalid salary or experience.');
            }

        } else if (command === 'RETRIEVE') {
            try {
                const emps = await User.find({});
                const emplist = emps.map(emp =>
                    `ID: ${emp.userId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                ).join('\n');

                ws.send(emplist || 'No employees found.');
            } catch (err) {
                ws.send('Error retrieving employees.');
            }

        } else if (command === 'RETRIEVE_BY_DEPT' && parts.length === 2) {
            try {
                const emps = await User.find({ department: parts[1] });
                const emplist = emps.map(emp =>
                    `ID: ${emp.userId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
                ).join('\n');

                ws.send(emplist || 'No employees found.');
            } catch (err) {
                ws.send('Error retrieving employees by department.');
            }

        } else {
            ws.send('Invalid command.');
        }
    });

    ws.on('close', () => {
        console.log('Client disconnected.');
    });
});

console.log('WebSocket server running on port 8081');