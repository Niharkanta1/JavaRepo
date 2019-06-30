#!/bin/bash
DOWNLOAD_SCRIPT="https://raw.githubusercontent.com/ericek111/java-csgo-externals/master/download.sh"
START_SCRIPT="https://raw.githubusercontent.com/ericek111/java-csgo-externals/master/start.sh"
MAIN_RELEASES_URL="https://api.github.com/repos/ericek111/java-csgo-externals/releases"
JMM_RELEASES_URL="https://api.github.com/repos/ericek111/Java-Memory-Manipulation/releases"
TEXTURES_ZIP="https://github.com/ericek111/java-csgo-externals/releases/download/1.4/textures.zip"

self_update=false
updated=false

if [ "$1" == "-noup" ]; then
	self_update=false;
fi

if [ "$(whoami)" == 'root' ]; then
	echo "WARNING: You should *NOT* run this script as root!"
	echo "Do not trust any scripts that are not written by you!"
	echo "Malicious script can delete data on your computer, connected devices and network drives."
	echo "Always make sure what does a command or script you're about to execute really do."
	read -p "> Continue? " -n 1 -r
	if [[ ! $REPLY =~ ^[Yy]$ ]]; then
	    exit 0;
	fi
fi

ABSPATH=`pwd`
mkdir -p "$ABSPATH/lib"

if [ "$self_update" == "true" ]; then
	wget_output=$(wget -4 -N -q -O "$ABSPATH/download_new.sh" "$DOWNLOAD_SCRIPT")
	if [ $? -ne 0 ]; then
		rm "$ABSPATH/download_new.sh" 2> /dev/null
		echo "$wget_output"
		echo "WARNING: Failed to download the latest version of this script! Some libraries might be outdated."
		read -p "> Continue? " -n 1 -r
		if [[ ! $REPLY =~ ^[Yy]$ ]]; then
		    exit 0;
		fi
	else
		chmod +x "$ABSPATH/download_new.sh"
		updated=true
		bash "$ABSPATH/download_new.sh"
	fi
fi


function downloaddep {
	if [ -f "$ABSPATH/lib/$(basename "$2")" ]; then
		echo "$(basename "$2") already exists! Skipping..."
		return 0;
	fi
	wget_output=$(wget -4 -q -N  -O "$1/$(basename "$2")" "$2")
	if [ $? -ne 0 ]; then
		rm "$ABSPATH/lib/$(basename "$2")"  2> /dev/null
		echo "$wget_output"
		echo "ERROR: Failed to download $2"
		exit 1;
	else
		echo "Downloaded $2"
	fi
}
function downloadmain {
	gitreleases=$(wget -4 -O - -o /dev/null "$MAIN_RELEASES_URL")
	if [ $? -ne 0 ]; then
		echo "ERROR: Failed to fetch GitHub releases!"
		exit 1;
	else
		mainjarurl=$(echo "$gitreleases" | grep browser_download_url | head -n 1 | cut -d '"' -f 4)
		wget_output=$(wget -4 -N -q -O "$ABSPATH/CSGOExternals.jar" "$mainjarurl")
		if [ $? -ne 0 ]; then
			rm "$ABSPATH/CSGOExternals.jar"  2> /dev/null
			echo "$wget_output"
			echo "ERROR: Failed to download the main release JAR! $mainjarurl"
			exit 1;
		else
			echo "Downloaded the main JAR!"
		fi
	fi
}
function downloadjmm {
	gitjmmreleases=$(wget -4 -O - -o /dev/null "$JMM_RELEASES_URL")
	if [ $? -ne 0 ]; then
		echo "ERROR: Failed to fetch GitHub releases!"
		exit 1;
	else
		jmmjarurl=$(echo "$gitjmmreleases" | grep browser_download_url | head -n 1 | cut -d '"' -f 4)
		wget_output=$(wget -4 -N -q -O "$ABSPATH/lib/Java-Memory-Manipulation.jar" "$jmmjarurl")
		if [ $? -ne 0 ]; then
			rm "$ABSPATH/Java-Memory-Manipulation.jar"  2> /dev/null
			echo "$wget_output"
			echo "ERROR: Failed to download Java-Memory-Manipulation library! $jmmjarurl"
			exit 1;
		else
			echo "Downloaded Java-Memory-Manipulation library!"
		fi
	fi
}
function downloadstart {
	wget_output=$(wget -4 -q -N -O "$ABSPATH/start.sh" "$START_SCRIPT")
	if [ $? -ne 0 ]; then
		echo "$wget_output"
		echo "ERROR: Failed to download the start script!"
		exit 1;
	else
		echo "Downloaded the start script!"
		chmod +x "$ABSPATH/start.sh"
	fi
}
function downloadtextures {
	wget_output=$(wget -4 -q -N -O "$ABSPATH/textures.zip" "$TEXTURES_ZIP")
	if [ $? -ne 0 ]; then
		echo "$wget_output"
		echo "ERROR: Failed to download textures.zip!"
		exit 1;
	else
		echo "Downloaded textures.zip!"
		unzip -q -o "$ABSPATH/textures.zip"
		rm "$ABSPATH/textures.zip"
	fi
}

echo ""
echo ">>> Downlading dependencies..."
downloaddep "$ABSPATH/lib" "http://repo1.maven.org/maven2/net/openhft/zero-allocation-hashing/0.8/zero-allocation-hashing-0.8.jar"
downloaddep "$ABSPATH/lib" "http://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.0/gson-2.8.0.jar"
downloaddep "$ABSPATH/lib" "http://repo1.maven.org/maven2/net/java/dev/jna/jna/4.4.0/jna-4.4.0.jar"
downloaddep "$ABSPATH/lib" "http://repo1.maven.org/maven2/net/java/dev/jna/jna-platform/4.4.0/jna-platform-4.4.0.jar"
downloaddep "$ABSPATH/lib" "http://repo1.maven.org/maven2/it/unimi/dsi/fastutil/7.1.0/fastutil-7.1.0.jar"
downloaddep "$ABSPATH/lib" "https://jogamp.org/deployment/jogamp-current/jar/gluegen-rt.jar"
downloaddep "$ABSPATH/lib" "https://jogamp.org/deployment/jogamp-current/jar/gluegen-rt-natives-linux-amd64.jar"
downloaddep "$ABSPATH/lib" "https://github.com/ericek111/java-csgo-externals/releases/download/1.0/jogl-all.jar"
downloaddep "$ABSPATH/lib" "https://github.com/ericek111/java-csgo-externals/releases/download/1.0/jogl-all-natives-linux-amd64.jar"
downloadjmm
echo ">>> Dependencies downloaded!"

downloadtextures
downloadmain
downloadstart

mkdir -p "$ABSPATH/scripts"
echo "bind Alt_L glow toggle" >> "$ABSPATH/scripts/autoexec.txt"
echo "bind kp_end disablepp toggle" >> "$ABSPATH/scripts/autoexec.txt"

if [ "$updated" = true ] ; then
	rm "$ABSPATH/download.sh" 2> /dev/null
    mv "$ABSPATH/download_new.sh" "$ABSPATH/download.sh"
fi
