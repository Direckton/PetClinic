<%-- 
    Document   : Pet
    Created on : Nov 29, 2023, 8:40:09 PM
    Author     : direc
--%>

<jsp:include page="head.jsp" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<body onload="getPetTable('searchId','pets');">

<div class="container">
    <div class="row">
        <p class="fs-1 fw-bold">Pet Registry</p>

    </div>
    <div class="row">
        <div class="col col-md-3">
                <div class="input-group ">
                    <input type="text" id ="searchId" name ="searchId"  class="form-control " placeholder="Search">
                    <button type="button" class="btn btn-outline-success" onClick="getPetTable('searchId','pets');">Search</button>
                </div>
        </div>
    </div>
    <div class="row">   
        <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Animal</th>
                <th scope="col">Age</th>
                <th scope="col">Health</th>
                <th scope="col">Visit</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
        </thead>
        <tbody id="pets">
            
        </tbody>
        
        </table>
    </div>
    <div class="row">
  
  <!-- Modal -->
  <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Edit Pet</h5>
          <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal"></button>
        </div>
        <form class="">
            <div class="row g-3 justify-content-md-center modal-body">
                <div class="col-1">
                  <label for="staticId" class="">#</label>
                  <input type="text" readonly class="form-control-plaintext" id="staticId" value="1">
                </div>
                <div class="col-auto">
                  <label for="inputAnimal" class="">Animal</label>
                  <input type="text" class="form-control" id="inputAnimal" value="Cat">
                </div>
                <div class="col-2">
                    <label for="inputAge" class="">Age</label>
                    <input type="number" class="form-control" id="inputAge" value="1">
                </div> 
                <div class="col-auto">
                    <label for="inputHealth" class="">Health</label>
                    <select class="form-select" id="inputHealth" aria-label="Default select example">
                        <option value="1" selected>Healthy</option>
                        <option value="2">Sick</option>
                        <option value="3">N/A</option>
                      </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </form>
      </div>
    </div>
  </div>
    
</div>
</body>

    <script type="text/javascript" src="js/ajax.js"></script>
</html>
