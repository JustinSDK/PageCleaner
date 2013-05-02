<?php
    header('Content-Type:text/html');
    $keywords = array("caterpillar", "car", "ceo", "c++", "justin", "java", "javascript");
    $keyword = $_GET['keyword'];
    $count = count($keywords);
    for ($i = 0; $i < $count; $i++) {
        $pos = strpos($keywords[$i], $keyword);
        if($pos !== false) {
            $result[] = $keywords[$i];
        }
    }
    $count = count($result) - 1; 
    echo '[';
    for ($i = 0; $i < $count; $i++) {
        echo "\"" . $result[$i] . "\",";
    }
    if($count !== -1) {
        echo "\"" . $result[$count] . "\"";
    }
    echo "]";
?>