<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
    <link rel="stylesheet" href="/css/main.css">
    <div class="wrapper"> 
        <div class="map">
            <div id="score"><h1 id="stopwatch">00:00:00</h1></div>
            <canvas id="canvas" class="canvas" width="600" height="700"></canvas>
        </div>
    </div>
    <script src="/js/test.js"></script>