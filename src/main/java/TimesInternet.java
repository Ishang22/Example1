//Implement the LRUCache class:
//        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//        int get(int key) Return the value of the key if the key exists, otherwise return -1.
//        void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//        The functions get and put must each run in O(1) average time complexity.
//
//
//class Node
//{
//    int key;
//    int data;
//    Node prev;
//    Node next;
//    Node(int data,int key)
//    {
//        this.data=data;
//        this.key=key;
//        prev=null;
//        next=null;
//    }
//    int getData()
//    {
//        return data;
//    }
//
//
//    int getKey()
//    {
//        return key;
//    }
//}
//
//
//class LRU
//{
//    Node head =null,tail=null;
//    HashMap<Integer,Node> map=new HasMap<>();
//    int capacity=0;
//    void insert(Node node)
//    {
//        map.put(node.getKey(),node);
//        Node nextHead= head.next;
//        head.next=node;
//        node.prev=head;
//        node.next=nextHead;
//        nextHead.prev=node;
//    }
//    void remove(Node node)
//    {
//        map.remove(node.getKey());
//        node.prev.next=node.next;
//        node.next.prev=node.prev;
//    }
//
//
//    int get(int key)
//    {
//        if(!map.containsKey(key))
//        {
//            return -1;
//        }
//        Node node=map.get(key);
//        remove(node);
//        insert(node);
//        return node.getData();
//
//
//    }
//
//
//    void put(int key, int value)
//    {
//        if(map.containsKey(key))
//        {
//            Node node=map.get(key);
//            remove(node);
//        }
//        if(map.size()==capacity)
//        {
//            remove(tail.prev);
//        }
//
//
//        insert(new Node(key,value));
//
//
//
//
//    }
//
//
//    LRU(int capacity)
//    {
//        this.capacity=capacity;
//        head =Node(0,0);
//        tail = Node(0,0);
//        head.next=tail;
//        tail.prev=head;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//Correlated -
//
//
//        explain Select * from table1 t1 exists ( select * from table2 t2.id>t1.id)
//        explain select * from table table1 t1 join table2 t2 on t1.id==t2.id where—-(filter)
//
//
//
//
//
//
//
//
//        employee
//        name
//        mobile
//        department
//        role
//        doj
//
//
//        However, composite index will only be used when both columns are included in a query.
//        If only one column is queried, composite index will not be used.
//        index: (mobile, department)
//        select * from employee where name = ‘a’ and mobile = ‘’;
//        select * from employee where name = ‘a’ and department = ‘’;
//
//
//        Problem Statement: (seq: 100001)
//
//
//        on 1st April 23, ABC23100001, ABC23100002, ABC23100003
//        ABC22100001
//
//
//
//
//        (retional- postgres,mysql)
//        id    intansnce1  key1    key2
//        1            1             ABC     23
//        2
//
//
//
//
//
//
//        Invoice table(cassendra (read+write))
//        id     invoiceId       s3Url
//
//
