<% 
	String header = request.getParameter("header");
	String body = request.getParameter("body");
%>

<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign"> <%= header %> <span class="glyphicon glyphicon-exclamation-sign"></span></h4>
        </div>
        <div class="modal-body">
          <p><%= body %></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"">Confirmar</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
      
    </div>
  </div>