#!/bin/bash

# Zookeeper:
if [ -d "/home/vagrant/Apps/Zookeeper" ]; then
    echo "Start Zookeeper"
    sudo /home/vagrant/Apps/Zookeeper/zookeeper-3.3.6/bin/zkServer.sh start
else
    echo "Installing Zookeeper"
    # install
    cd ~
    wget http://apache.proserve.nl/zookeeper/zookeeper-3.3.6/zookeeper-3.3.6.tar.gz
    mkdir /home/vagrant/Apps/Zookeeper
    # extract
    tar -xvf zookeeper-3.3.6.tar.gz -C /home/vagrant/Apps/Zookeeper
    chown -R vagrant /home/vagrant/Apps/Zookeeper
    # configure
    cd /home/vagrant/Apps/Zookeeper/zookeeper-3.3.6/conf/
    mv zoo_sample.cfg zoo.cfg
    mkdir /tmp/zookeeper
    # start
    sudo /home/vagrant/Apps/Zookeeper/zookeeper-3.3.6/bin/zkServer.sh start
fi

# Microservice Admin Dashboard:
if [ -d "/home/vagrant/Apps/Dashboard" ]; then
   if lsof -Pi :8080 -sTCP:LISTEN -t >/dev/null ; then
     echo "Dashboard is already running"
   else
    echo "Start Dashboard"
    cd /home/vagrant/Apps/Dashboard && java -jar dashboard-0.8.2.jar server config.yml &
   fi
else
   echo "Installing Dashboard"
   # install
   cd ~
   mkdir /home/vagrant/Apps/Dashboard
   cd /home/vagrant/Apps/Dashboard
   wget https://github.com/abduegal/Microservice-Admin-Dashboard/raw/master/dashboard-release-0.8.2.zip

   # extract
   unzip -j dashboard-release-0.8.2.zip
   chown -R vagrant /home/vagrant/Apps/Dashboard

   #start
   cd /home/vagrant/Apps/Dashboard && java -jar dashboard-0.8.2.jar server config.yml &
fi
