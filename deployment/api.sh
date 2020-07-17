#!/bin/bash

version="build.12"
baseuri="https://api.felleskomponent.no"

cat template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME felles-kodeverk -p BASE_URI ${baseuri}/felles/kodeverk -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
cat template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME utdanning-vigokodeverk -p BASE_URI ${baseuri}/utdanning/vigo/kodeverk -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -

component="administrasjon"
for stack in "personal" "organisasjon" "kodeverk" "fullmakt" "arkiv"
do
    cat template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
done

component="utdanning"
for stack in "elev" "utdanningsprogram" "vurdering" "timeplan" "kodeverk"
do
    cat template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
done
