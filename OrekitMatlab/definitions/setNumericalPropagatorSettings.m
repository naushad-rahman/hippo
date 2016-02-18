function [position_tolerance, min_step, max_step, duration, step_size, choiceofProp] = setNumericalPropagatorSettings()

position_tolerance = 100; %m
min_step = 0.001;
max_step = 1000;
step_size = 30;
duration = 10000;
choiceofProp = 0;
% duration = 24525000*2;