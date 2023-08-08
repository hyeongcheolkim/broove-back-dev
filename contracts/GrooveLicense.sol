// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "../contracts-protocol/KIP/token/KIP17/KIP17.sol";

contract GrooveLicense is KIP17{
    constructor(string memory uri_) KIP37(uri_) {}
}
