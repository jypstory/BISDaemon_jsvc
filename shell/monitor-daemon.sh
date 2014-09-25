===================== monitor-daemon.sh 
#!/bin/sh
JAVA_HOME=/app/jdk
DAEMON_HOME=${PWD}
JSVC=${DAEMON_HOME}/bin/jsvc
USER=bi_svc_bis

PID_FILE=$DAEMON_HOME/daemon.pid
OUT_FILE=$DAEMON_HOME/daemon.out
ERR_FILE=$DAEMON_HOME/daemon.err

CLASSPATH=\
$DAEMON_HOME/lib/*:\
$DAEMON_HOME/BISSmsDaemon.jar

MAIN_CLASS=com.skplanet.monitoring.daemon.service.impl.MainDaemonImpl
case "$1" in
      start)
      #
      # Start Daemon
      #
      rm -f $OUT_FILE
      $JSVC \
      -user $USER \
      -java-home $JAVA_HOME \
      -pidfile $PID_FILE \
      -outfile $OUT_FILE \
      -errfile $OUT_FILE \
      -cp $CLASSPATH \
      -DCueOutputDir=/data/CUE_LOG \
      -DProfDir=/app/home/bi_svc_bis/bis_services/MON/opr/conf \
      $MAIN_CLASS
      #
      # To get a verbose JVM
      #-verbose \
      # To get a debug of jsvc.
      #-debug \
exit $?
;;

      stop)
      #
      # Stop Daemon
      #
      $JSVC \
      -stop \
      -nodetach \
      -java-home $JAVA_HOME \
      -pidfile $PID_FILE \
      -outfile $OUT_FILE \
      -errfile $OUT_FILE \
      -cp $CLASSPATH \
      $MAIN_CLASS
exit $?
;;

*)
     echo "[Usage] monitor-daemon.sh start | stop"
     exit 1;;
esac
