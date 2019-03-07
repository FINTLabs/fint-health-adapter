$version = "build.7"
$baseuri = "https://api.felleskomponent.no"

cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME felles-kodeverk -p BASE_URI ${baseuri}/felles/kodeverk -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME utdanning-vigokodeverk -p BASE_URI ${baseuri}/utdanning/vigo/kodeverk -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -

$component = "administrasjon"
foreach ($stack in "personal", "organisasjon", "kodeverk", "fullmakt") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
}

$component = "utdanning"
foreach ($stack in "elev", "utdanningsprogram", "vurdering", "timeplan", "kodeverk") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
}
