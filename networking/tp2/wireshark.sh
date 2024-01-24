tail -n+0 -f ~/R1_eth0.cap | wireshark -SHkli - &
tail -n+0 -f ~/R1_eth1.cap | wireshark -SHkli - &
