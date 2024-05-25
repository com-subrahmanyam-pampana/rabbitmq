
# Install
scp rabbitmq_install.sh root@x.x.x.x:
chmod +x rabbitmq_install.sh
# Step 2
./rabbitmq_install.sh
apt --fix-broken install

#
sudo systemctl start rabbitmq-server

Check on port 5672
