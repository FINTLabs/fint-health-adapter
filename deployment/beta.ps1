$version = "build.3"
$baseuri = "https://beta.felleskomponent.no"

$component = "administrasjon"
foreach ($stack in "personal", "organisasjon", "kodeverk", "fullmakt") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
}

$component = "utdanning"
foreach ($stack in "elev", "utdanningsprogram", "vurdering", "timeplan", "kodeverk") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version -p ENABLE_OAUTH true | kubectl apply -f -
}
