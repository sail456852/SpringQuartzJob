var F_merchant_id = '123456789'
var db='lepos'+F_merchant_id.substr(-3,1);
console.log(db);
var table_no='t_order_by_merchant_'+F_merchant_id.substr(-2,2);
console.log(table_no);
var dbt=db+'.'+table_no;
console.log(dbt);


var db='lepos';
var table_no='t_merchant_fee_rate_'+F_merchant_id.substr(-2,2);
console.log(table_no);
var dbt=db+'.'+table_no;
console.log(dbt);
