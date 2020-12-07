function greeting(){
    console.log("Hej");
}

function preventRegisterFormFromSending(registerForm){
    registerForm.submit(function (event){
        event.preventDefault();
        registerUser($("#username").val(), $("#password").val());
    });
}

function registerUser(username, password){
    console.log("registerUser er kaldet med " + username + "og" + password);
    var registerObject = {};
    registerObject["username"] = username;
    registerObject["password"] = password;
    $.ajax({
        url:"/api/register",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(registerObject),
        success:function (data){
            console.log("SUCCESS svar fra server")
        },
        error:function (data){
            console.log("ERROR i svar fra server")
        }
    });
}

function checkjQuery(){
    if(typeof jQuery !== undefined){
        console.log("jQuery er loaded")
    }else {
        console.log("jQuery er IKKE loaded")
    }
}