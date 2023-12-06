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
</div>
</body>

    <script type="text/javascript" src="js/ajax.js"></script>
</html>
