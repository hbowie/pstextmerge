#!/bin/sh

Name="PSTextMerge"
name="pstextmerge"
version="5.00"
docs="/Users/hbowie/PSPub Docs"
appf="dist/appfolder"
cp 	-p -v "$docs/plists/$Name/Info.plist" package/macosx
mkdir -p -v $appf
mkdir -p -v $appf/help
cp -p -v "$docs/help/$name-user-guide.html" $appf/help
cp -p -v "$docs/help/$name-history.html" $appf/help
cp -p -v "$docs/help/styles.css" $appf/help
mkdir -p -v $appf/logos
cp -p -v "$docs/logos/$name.png" $appf/logos/$name.png
mkdir -p dist/appfolder
jdk=$(/usr/libexec/java_home)
$jdk/bin/javapackager -version
$jdk/bin/javapackager -deploy \
	-native dmg \
	-srcdir dist \
    -srcfiles $name.jar \
    -srcfiles lib \
    -srcfiles appfolder \
    -appclass com.powersurgepub.$name.$Name \
    -name $Name \
    -title "Do interesting things with text files" \
    -BappVersion=$version \
    -outdir deploy \
    -outfile $name \
    -v

