$version = "build.3"
$baseuri = "https://play-with-fint.felleskomponent.no"

$component = "administrasjon"
foreach ($stack in "personal", "organisasjon", "kodeverk") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version | kubectl --namespace=pwf apply -f -
}

$component = "utdanning"
foreach ($stack in "elev", "utdanningsprogram", "vurdering", "timeplan", "kodeverk") {
    cat .\template.yaml | docker run -i jimmycuadra/ktmpl - -p SERVICE_NAME ${component}-${stack} -p BASE_URI ${baseuri}/${component}/${stack} -p VERSION $version | kubectl --namespace=pwf apply -f -
}
