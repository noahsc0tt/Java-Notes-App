// This file is used to create a button that deletes a note

<form action="/delete_note" method="POST">
  <input type="hidden" name="key" value="<%=request.getAttribute("key")%>">
  <button type="submit">Delete Note</button>
</form>
