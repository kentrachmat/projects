tail -n+0 -f ~/box_eth0.cap | wireshark -SHkli - &
tail -n+0 -f ~/box_eth1.cap | wireshark -SHkli - &