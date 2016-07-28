-------------------
Map
-------------------
function (doc, meta) {
  if(doc.status == "STOCKED"){
  	emit(doc.distributionCenter+"::"+doc.productId, null);
  }
}
---------------------
Reduce
---------------------
_count