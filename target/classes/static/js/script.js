function chkFields() {
    if(document.getElementById("inputName").value==="" ||
        document.getElementById("inputSurname").value==="" ||
        document.getElementById("inputCardID").value==="")
    {
        document.getElementById('regBtn').disabled = true;
    } else {
        document.getElementById('regBtn').disabled = false;
    }
}