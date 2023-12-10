
function getResult(arg1, arg2, result) {
  var xhttp = new XMLHttpRequest();
  
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        document.getElementById(result).innerHTML = this.responseText;
    }
  };
  
  var ar1 = document.getElementById(arg1).value;
  var ar2 = document.getElementById(arg2).value;
  
  xhttp.open("GET", "simplecalculator?arg1=" + ar1 + "&arg2=" + ar2, true);
  xhttp.send();
}

function getTable(firstName, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  
  xhttp.open("GET", "persons?firstName="+document.getElementById(firstName).value, true);
  xhttp.send();
}
function getPetTable(searchId, tableId) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
     document.getElementById(tableId).innerHTML = this.responseText;
    }
  };
  
  xhttp.open("GET", "pets?searchId="+document.getElementById(searchId).value, true);
  xhttp.send();
}

function sendPetId(ID) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      //document.getElementById(tableId).innerHTML = this.responseText;
    }
  };

  xhttp.open("POST", "pets?searchId="+document.getElementById(searchId).value, true);
  xhttp.send();
}

// $('#exampleModal').on('shown.bs.modal', function () {
//   $('#launch-modal').trigger('focus')
// })

function getPet(petId)
{
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      let input  = this.responseText.replace('[','');
      input = input.replace(']','');
      let data = input.split(", ");

      document.getElementById('staticId').value = data[0];
      document.getElementById('inputAnimal').value = data[1];
      document.getElementById('inputAge').value = data[2];
      document.getElementById('inputHealth').value = data[3];

    }
  };

  xhttp.open("GET", "pets?editId="+petId, true);
  xhttp.send();
}

function getVisit(visitId)
{
  let xhttp = new XMLHttpRequest();
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);

  let petId = urlParams.get('id');
  document.getElementById('petId').value = petId;
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      let input  = this.responseText.replace('[','');
      input = input.replace(']','');
      let data = input.split(", ");

      document.getElementById('staticId').value = data[0];
      document.getElementById('inputDate').value = data[1];
      document.getElementById('inputTime').value = data[2];
      document.getElementById('inputCost').value = data[3];
    }
  };

  console.log(petId);

  xhttp.open("GET", "visits?editId="+visitId + "&petId=" + petId, true);
  xhttp.send();
}


function getVisitTable(tableId){
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      document.getElementById(tableId).innerHTML = this.responseText;
      document.getElementById('petId').value = id;
    }};
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const id = urlParams.get('id');


  xhttp.open("GET", "visits?id="+id, true);
  xhttp.send();
}

function handleCheckbox(checkbox, visitId, petId){
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
    }};

  console.log(checkbox.checked);
  xhttp.open("POST", "/visits", true);
  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhttp.send("checkbox=" + checkbox.checked + "&visitId=" + visitId + "&petId=" + petId);
}

function addVisit()
{
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      location.reload();
    }};
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const id = urlParams.get('id');

  xhttp.open("POST", "/visits", true);
  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

  xhttp.send("add&petId=" + id);

}