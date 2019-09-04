# Operations on Znode using Zookeeper APIs in Java

This repository consists of set of operations performed on Znodes using Zookeeper APIs.I have used Zookeeper Version 3.4.10 and performed the following : 

1) Created a Znode
2) Check for existence of a znode
3) Get data for a Znode
4) Set data for a Znode
5) Get children for that Znode
6) Delete the Znode


The ZookeeperConnection.java is used by each other Znode operation code and is used in establishing connection with the Zookeeper ensemble.
Before using these,start the Zookeeper service via the command :

 ./zkServer.sh start zoo.cfg
 
 and to check for changes,see the CLI.To get the CLI type the command :
 
 ./zkCli.sh -server 127.0.0.1:2181
