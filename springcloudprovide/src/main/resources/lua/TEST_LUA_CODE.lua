local teststring = ARGV[1]
local testName='SPRINGCLOUDPARENT:testname:'..KEYS[1]
redis.call('select',3)
local result = redis.call('get',testName)
if not result then
   return testName
end
return teststring..result


