#!/bin/bash

if ! command -v pandoc &> /dev/null
then
    echo "pandoc could not be found"
    exit
fi

rm -rf adoc
mkdir -p adoc
i=0
for eachfile in *.docx; do
  file_name=$(echo "$eachfile" | cut -f 1 -d '.')
  pandoc "$file_name".docx \
    -f docx \
    -t asciidoc \
    --wrap=none \
    --extract-media="$file_name"-images \
    -o "$file_name".adoc
  mv $file_name.adoc adoc
  mv $file_name-images adoc 2>/dev/null
  for file in adoc/$file_name.adoc; do
    if [ -e "$file" ]; then
      i=$(($i + 1))
    fi
  done
done

read -p "$i file(s) converted. Press Enter to exit"
