��Ʒ�ķ���
	
	���������֮����ʲô��ϵ?
		һ�Զ�
		
		��װ		һ������
			��װ		��������
			
			
		������
			create table t_producttype(
				id int primary key auto_increment,
				ptname varchar(31),
				ptdesc varchar(255),
				ptdeleted int,
				parent_id int
			)
			
			--------------------------------------------
			
			id     ptname    ptdesc   ptdeleted   parent_id
			1		��װ		  ��װx		1			null
			2		��ʳ		 ��ʳb		1   		null
			3		��װ		��װc			1			1
			4		Ůװ		Ůװ			1			1
			5		�ɿ���	�ɿ���		1			2
			6		�ҵ�		�ҵ�			1			null
			7		��ܽ�ɿ���	xx			1			5
			8		����		����			1			2
			
			
			������Ҫ��ѯһ������
					select * from t_producttype where parent_is is null
					
				��Ҫ��ѯ��װ�Ķ�������parent_id = 1
					select * from t_producttype where parent_id = 1
					
					
				// һ������id= 2
				select * from t_producttype where parent_id = 1
				
				for(  �ɿ���������    ){
					
					for( �ɿ�������Ķ�������	){
					
						for(){
							// 1��3��
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

--------------------��ƶ���ʵ��-------------------------------

Order{
	
	// id
	// �µ�ʱ��
	// ������״̬
	// User
	// ������
	// �����ļ۸�
	// ����ʱ��
	// ����
	// �����ĸ��ʽ
	// List<OrderItem>
}

OrderItem{

	// id
	// ��Ʒ
	// ����
	// С��
	// ������
}

---------------
�û��Ͷ����Ĺ�ϵ��
	һ���û��ж������
	һ������ֻ������ĳһ���û�
	
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

�鶩���Ǹ���ʲô��?
	�����û���ѯ����
	
����״̬����ת���ȥ��ת?
	Ҳ����˵����״̬�ĸı�����ȥ�ı�?
	
�����ر�?(�������ʱ)
	
	����һ���¼���
		30�ֵ���
		

����jar��
	
	1������jar��
	
	2������spring���ļ�
		
		
	
	
	
	
	
		












			
			
			
			
			
			
	
	