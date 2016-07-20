#!/bin/bash

cnt=0
rm -f ./output/*.*
fis=`ls ./input/`

for i in $fis
do
	cnt=$(($cnt+1))
	echo "$i"
	echo $cnt
	cp ./input/"$i" ./output/"$cnt".apk

	#cp ./output/"$cnt".apk ./output/"$cnt"-resigned.apk
	#java -classpath ./Signapk/testsign.jar testsign ./output/"$cnt"-resigned.apk
	
	#./Signapk/zipalign -f 8 ./output/"$cnt".apk ./output/"$cnt"-za.apk

	#cp ./output/"$cnt".apk ./apktool/"$cnt".apk

	

	./apktool/apktool d ./apktool/"$cnt".apk ./apktool/"$cnt"
	java InsertM apktool/"$cnt"
	./apktool/apktool b ./apktool/1 ./output/"$cnt"-of-insert.apk 
	rm -rf ./apktool/"$cnt" 

	./apktool/apktool d ./apktool/"$cnt".apk ./apktool/"$cnt"
	java InsertDeadCode apktool/"$cnt"
	./apktool/apktool b ./apktool/"$cnt" ./output/insertDead.apk 
	rm -rf ./apktool/"$cnt" 

	 

	./apktool/apktool d ./apktool/"$cnt".apk ./apktool/"$cnt"
	java CntrolFlowChange apktool/"$cnt"
	./apktool/apktool b ./apktool/"$cnt" ./output/"$cnt"-of-changeCFG.apk 
	rm -rf ./apktool/"$cnt" 

	
	./apktool/apktool d ./apktool/"$cnt".apk ./apktool/"$cnt"
	cp -R ./apktool/com ./apktool/"$cnt"/smali/
	java StringOb apktool/"$cnt"
	./apktool/apktool b ./apktool/"$cnt" ./output/"$cnt"-of-stringE.apk 
	rm -rf ./apktool/"$cnt" 

	

	
	#./apktool/apktool d ./apktool/"$cnt".apk ./apktool/"$cnt"
	#cp -R ./apktool/com ./apktool/"$cnt"/smali/
	
	#./apktool/apktool b ./apktool/"$cnt" ./output/"$cnt"-of-stringE.apk 
	#rm -rf ./apktool/"$cnt" 

################################ Add Your Extension Obfuscator Here ######################################







################################ Add Your Extension Obfuscator Here ######################################

	
	java -classpath ./Signapk/testsign.jar testsign ./output/"$cnt"-of-insert.apk
	java -classpath ./Signapk/testsign.jar testsign ./output/"$cnt"insertDead.apk
	java -classpath ./Signapk/testsign.jar testsign ./output/"$cnt"-of-changeCFG.apk
	java -classpath ./Signapk/testsign.jar testsign ./output/"$cnt"-of-stringE.apk
	
	rm -f ./apktool/"$cnt".apk
done
