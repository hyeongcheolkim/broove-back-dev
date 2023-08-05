// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "../contracts-protocol/KIP/token/KIP17/KIP17.sol";

contract Groove is KIP17 {
    constructor(string memory name_, string memory symbol_) KIP17(name_, symbol_) {}
}
