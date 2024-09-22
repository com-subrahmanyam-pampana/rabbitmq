Step I : Install Essential Dependencies
First thing we have to do update & upgrade the system. Then we will install the require dependencies like curl & gnupg.

sudo apt-get update 
sudo apt-get upgrade -y
sudo apt-get install curl gnupg -y
Step II : Enable apt HTTPS Transport
In order to download RabbitMQ & Erlang packages from launchpad, the apt-transport-https package must be installed. This package allow apt to use package repository over HTTPS

sudo apt-get install apt-transport-https -y
Step III : Add Repository Signing Keys
To use RabbitMQ Repository, We have to configure it’s GPG key on our system for validation.

## Team RabbitMQ's main signing key
curl -1sLf "https://keys.openpgp.org/vks/v1/by-fingerprint/0A9AF2115F4687BD29803A206B73A36E6026DFCA" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/com.rabbitmq.team.gpg > /dev/null
## Launchpad PPA that provides modern Erlang releases
curl -1sLf "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0xf77f1eda57ebb1cc" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg > /dev/null
## PackageCloud RabbitMQ repository
curl -1sLf "https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey" | sudo gpg --dearmor | sudo tee /usr/share/keyrings/io.packagecloud.rabbitmq.gpg > /dev/null
Step IV : Add a Source List File
As it is 3rd party apt repository, So we have configure it on our system. So below steps we have to follow,

Open the following file with your preferred Text Editor
vi /etc/apt/sources.list.d/rabbitmq.list
Copy below content to that file.
# Source repository definition example.

## Provides modern Erlang/OTP releases
##
## "distribution-name" as distribution name should work for any reasonably recent Ubuntu or Debian release.
## See the release to distribution mapping table in RabbitMQ doc guides to learn more.
deb [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu distribution-name main
deb-src [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu distribution-name main

## Provides RabbitMQ
##
## "distribution-name" as distribution name should work for any reasonably recent Ubuntu or Debian release.
## See the release to distribution mapping table in RabbitMQ doc guides to learn more.
deb [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ distribution-name main
deb-src [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ distribution-name main
Note : Replace the distribution-name with you current distribution code name,

Here is Ubuntu distribution name with code name list,
Ubuntu 22.04 LTS -> jammy
Ubuntu 20.04 LTS -> focal
Ubuntu 18.04 LTS -> bionic
Ubuntu 16.04 LTS -> xenial

Here is the one sample for Ubuntu 22.04 LTS,

## Provides modern Erlang/OTP releases
##
## "jammy" as distribution name should work for any reasonably recent Ubuntu or Debian release.
## See the release to distribution mapping table in RabbitMQ doc guides to learn more.
deb [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu jammy main
deb-src [signed-by=/usr/share/keyrings/net.launchpad.ppa.rabbitmq.erlang.gpg] http://ppa.launchpad.net/rabbitmq/rabbitmq-erlang/ubuntu jammy main
## Provides RabbitMQ
##
## "jammy" as distribution name should work for any reasonably recent Ubuntu or Debian release.
## See the release to distribution mapping table in RabbitMQ doc guides to learn more.
deb [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ jammy main
deb-src [signed-by=/usr/share/keyrings/io.packagecloud.rabbitmq.gpg] https://packagecloud.io/rabbitmq/rabbitmq-server/ubuntu/ jammy main
Step V : Updating the Index listing
After adding any repository, We have to update the apt packages indexing.

sudo apt-get update -y
Step VI : Install Erlang packages
Now it time to install the Erlang packages with following command,

## Install Erlang packages
sudo apt-get install -y erlang-base erlang-asn1 erlang-crypto erlang-eldap erlang-ftp erlang-inets erlang-mnesia erlang-os-mon erlang-parsetools erlang-public-key erlang-runtime-tools erlang-snmp erlang-ssl erlang-syntax-tools erlang-tftp erlang-tools erlang-xmerl
Step VII : Install RabbitMQ Server and It’s Dependencies
After the Erlang packages installation, We have to install RabbitMQ Server package with it’s dependencies,

## Install rabbitmq-server and its dependencies
sudo apt-get install rabbitmq-server -y --fix-missing
Step VIII : Managing the RabbitMQ Service
We will use following commands to manage RabbitMQ service,

## For SystemD
# To see the current status of RabbitMQ Service
sudo systemctl status rabbitmq-server
# To start the RabbitMQ Service
sudo systemctl start rabbitmq-server
# To stop the RabbitMQ Service
sudo systemctl stop rabbitmq-server
# To enable the RabbitMQ Service
sudo systemctl enable rabbitmq-server
# To disable the RabbitMQ Service
sudo systemctl disable rabbitmq-server
## For InitD
# To see the current status of RabbitMQ Service
sudo service rabbitmq-server status
# To start the RabbitMQ Service
sudo service rabbitmq-server start
# To stop the RabbitMQ Service
sudo service rabbitmq-server stop
Step IX : Installation RabbitMQ Server Web Dashboard


