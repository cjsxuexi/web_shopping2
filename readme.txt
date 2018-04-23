商品的分类
	
	分类与分类之间是什么关系?
		一对多
		
		服装		一级分类
			男装		二级分类
			
			
		表的设计
			create table t_producttype(
				id int primary key auto_increment,
				ptname varchar(31),
				ptdesc varchar(255),
				ptdeleted int,
				parent_id int
			)
			
			--------------------------------------------
			
			id     ptname    ptdesc   ptdeleted   parent_id
			1		服装		  服装x		1			null
			2		零食		 零食b		1   		null
			3		男装		男装c			1			1
			4		女装		女装			1			1
			5		巧克力	巧克力		1			2
			6		家电		家电			1			null
			7		德芙巧克力	xx			1			5
			8		辣条		辣条			1			2
			
			
			需求：我要查询一级分类
					select * from t_producttype where parent_is is null
					
				我要查询服装的二级分类parent_id = 1
					select * from t_producttype where parent_id = 1
					
					
				// 一级分类id= 2
				select * from t_producttype where parent_id = 1
				
				for(  巧克力、辣条    ){
					
					for( 巧克力下面的二级分类	){
					
						for(){
							// 1、3级
						}
					}
				
				}
				
---------------------------------
create table t_user(

	id int primary key auto_increment,
	username varchar(61) ,
	password varchar(61),
	email varchar(61),
	phone varchar(20),
	address varchar(61),
	deleted int,
	activation int,
	registerTime timestamp
)

--------------------设计订单实体-------------------------------

Order{
	
	// id
	// 下单时间
	// 订单的状态
	// User
	// 订单号
	// 订单的价格
	// 付款时间
	// 留言
	// 订单的付款方式
	// List<OrderItem>
}

OrderItem{

	// id
	// 商品
	// 数量
	// 小计
	// 订单号
}

---------------
用户和订单的关系？
	一个用户有多个订单
	一个订单只能属于某一个用户
	
	private Order order;
	
create table t_order_item(
	id int primary key auto_increment,
	productName varchar(31),
	productPrice decimal(10,2),
	qunantity int,
	orderItemPrice decimal(10,2),
	order_id int
)

-------------------------------------------

查订单是根据什么查?
	根据用户查询订单
	
订单状态的流转如何去流转?
	也就是说订单状态的改变该如何去改变?
	
订单关闭?(订单付款超时)
	
	触发一个事件：
		30分到了
		

导入jar包
	
	1、导入jar包
	
	2、配置spring的文件
		
		
	
	
	
	
	
		












			
			
			
			
			
			
	
	