delete from OrderItem;
delete from PurchaseOrder;
delete from CartReceiver;
delete from CartGetter;
delete from Invoice;
delete from Cart;
delete from Product;
delete from ScUser where userId > 3
delete from Company where companyId <> 1;
delete from Warehouse;
INSERT INTO Warehouse(address, warehouseName, warehouseAdminId, cityId) VALUES ('智选化学', '智选化学', 1, 342);
