### Send watchdog notifications to systemd

Lib for java daemons to send keep-alive messages to refresh the systemd watchdog timestamp using the shell utility /usr/bin/systemd-notify.
Or you can use this [project](https://github.com/ericbets/java-sysd-notify-watchdog) to do it natively. 

Use the version 1.1 build artifact.
```xml
	<groupId>org.tricorder</groupId>
	<artifactId>shell-sysd-notify-watchdog</artifactId>
	<version>1.1</version>
```  
 
#### Simple Usage
 
```java
	SysDNotifier() notifier = new SysDNotifier();
 	notifier.start();
```
 
#### Advanced Usage
```java
	SysDNotifier notifier = new SysDNotifier();
	notifier.setAuditor(new Auditor() {
		@Override
		public boolean allGood() {				
			//Do more extensive internal checking of the process, if ok, return true.
			//Returning false will have the effect of suppressing the keep-alive 
			//message, causing systemd to kill and restart the process. 
			return true;
		}
	});
	notifier.start();
 ```
 
#### .service file layout 
```shell
[Unit]
Description=My Service

[Service]
#PIDFile=/tmp/my-service.pid
ExecStart=/usr/lib/jvm/java-1.8.0/bin/java -jar /tmp/my-service.jar
WatchdogSec=120
Restart=on-failure
Environment=FILELOC=/usr/bin/systemd-notify
NotifyAccess=all
StartLimitInterval=2min
StartLimitBurst=4
StartLimitAction=none

[Install]
WantedBy=multi-user.target
```
  
