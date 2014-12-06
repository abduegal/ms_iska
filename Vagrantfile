# -*- mode: ruby -*-
# vi: set ft=ruby :
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  # Every Vagrant virtual environment requires a box to build off of.
  config.vm.box = "dev"
  config.vm.box_url = "https://googledrive.com/host/0B_1tLRFSVMYBNlIwSzJDSU1GR0E"
  config.vm.provision :shell, :path => "environment/install.sh"
  config.vm.provision :shell, :path => "environment/install_maven.sh"
  config.vm.provision :shell, :path => "environment/install_kafka.sh"

  config.vm.provider :virtualbox do |vb|
	vb.gui = true
	vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
	vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]
	vb.customize ["modifyvm", :id, "--vram", "128"]
	vb.customize ["modifyvm", :id, "--graphicscontroller", "vboxvga"]
	vb.customize ["modifyvm", :id, "--accelerate3d", "on"]
	vb.customize ["modifyvm", :id, "--ioapic", "on"]
	vb.customize ["modifyvm", :id, "--hwvirtex", "on"]
	vb.customize ["modifyvm", :id, "--clipboard", "bidirectional"]
	vb.memory = 6144
	vb.cpus = 2
  end

end
