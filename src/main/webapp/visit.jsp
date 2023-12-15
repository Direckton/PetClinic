<%-- Created by IntelliJ IDEA. User: Michael Date: 07.12.2023 Time: 12:08 To change this template use File | Settings |
  File Templates. --%>

  <jsp:include page="head.jsp" />

  <%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <body onload="getVisitTable('visits'); getDBStatus();">

    <div id="waring"></div>

      <div class="container">
        <div class="row">
          <p class="fs-1 fw-bold">Visit Registry</p>
        </div>
        <div class="row">
          <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Date</th>
                <th scope="col">Time</th>
                <th scope="col">Cost</th>
                <th scope="col">Took place</th>
                <th scope="col">Medicines</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
              </tr>
            </thead>
            <tbody id="visits">

            </tbody>
          </table>
        </div>
            <div class="row">

              <!-- Modal -->
              <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">Edit Pet</h5>
                      <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal"></button>
                    </div>
                    <form class="" method="POST" action="${pageContext.request.contextPath}/visits">
                      <div class="row g-3 justify-content-md-center modal-body">
                        <input type="hidden" id="petId" name="petId" value="1">
                        <div class="col-1">
                          <label for="staticId" class="">#</label>
                          <input type="text" readonly class="form-control-plaintext" id="staticId" name="staticId"
                            value="1">
                        </div>
                        <div class="col-auto">
                          <label for="inputDate" class="">Date</label>
                          <input type="date" class="form-control" id="inputDate" name="inputDate">
                        </div>
                        <div class="col-2">
                          <label for="inputTime" class="">Time</label>
                          <input type="time" class="form-control" id="inputTime" name="inputTime">
                        </div>
                        <div class="col-2">
                          <label for="inputCost" class="">Cost</label>
                          <input type="number" step="0.01" class="form-control" id="inputCost" name="inputCost"
                            value="1">
                        </div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Save changes" name="saveEdit">
                      </div>
                    </form>
                  </div>
                </div>
              </div>

            </div>
            <div class="row">
              <div class="col-2">
                <input type="submit" class="btn btn-primary" onclick="addVisit();" value="Add">
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>

    <script type="text/javascript" src="js/ajax.js"></script>

    </html>